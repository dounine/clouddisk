package com.dounine.clouddisk360.parser;

import com.dounine.clouddisk360.annotation.DependResult;
import com.dounine.clouddisk360.annotation.Parse;
import com.dounine.clouddisk360.captthread.CaptThread;
import com.dounine.clouddisk360.captthread.CaptchaThreadValidator;
import com.dounine.clouddisk360.captthread.CaptchaValidator;
import com.dounine.clouddisk360.exception.CloudDiskException;
import com.dounine.clouddisk360.parser.deserializer.captcha.Captcha;
import com.dounine.clouddisk360.parser.deserializer.captcha.DifferPressParameter;
import com.dounine.clouddisk360.parser.deserializer.differpre.DifferPress;
import com.dounine.clouddisk360.parser.deserializer.differpre.DifferPressConst;
import com.dounine.clouddisk360.parser.deserializer.differpre.DifferPressRequestInterceptor;
import com.dounine.clouddisk360.parser.deserializer.differpre.DifferPressResponseHandle;
import com.dounine.clouddisk360.parser.deserializer.login.Login;
import com.dounine.clouddisk360.parser.deserializer.login.LoginParameter;
import com.dounine.clouddisk360.parser.deserializer.login.LoginUserToken;
import com.dounine.clouddisk360.parser.deserializer.user.check.UserCheckLogin;
import com.dounine.clouddisk360.parser.deserializer.user.token.UserToken;
import com.dounine.clouddisk360.util.TimeUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.HttpClients;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

@Parse("分压重定向域名")
@DependResult(customInit = false, result = DifferPress.class)
public class DifferPressParser extends
		BaseParser<HttpGet, DifferPress, DifferPressConst, DifferPressParameter, DifferPressRequestInterceptor, DifferPressResponseHandle, DifferPressParser> {

	public static Map<String, DifferPressParser> DIFFER_PRESS_PARSERS = new HashMap<>();
	private Captcha captcha = null;
	private CaptchaParser captchaParser = null;

	public DifferPressParser() {
		super();
	}

	public DifferPressParser(LoginUserToken loginUser) {
		super(loginUser);
	}

	public static final Logger LOGGER = LoggerFactory.getLogger(DifferPressParser.class);

	@Override
	public HttpGet initRequest(DifferPressParameter parameter) {
		try {
			URIBuilder uri = new URIBuilder(CONST.URI_PATH);
			uri.setParameter(CONST.ST_KEY, TimeUtil.getTimeLenth(10));
			uri.setParameter(CONST.SID_KEY, StringUtils.EMPTY);
			uri.setParameter(CONST.KEEPALIVE_KEY, CONST.KEEPALIVE_VAL);
			HttpGet request = new HttpGet(uri.build());
			return request;
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public DifferPress parse() {
		if(hasException()){
			return execClouddiskException();
		}
		String account = loginUserToken.getAccount();
		CaptchaValidator captchaValidator = CaptchaThreadValidator.getCaptchaValidator(account);
		if((null!=captchaValidator&&!captchaValidator.isSuccess())){
			this.setCloudDiskException(new CloudDiskException("请验证帐户验证码成功后再操作."));
			return null;
		}
		DifferPressParser differPressParser = DIFFER_PRESS_PARSERS.get(account);
		if (null != differPressParser) {
			this.dataSmooth(differPressParser);
			DifferPress differPress = differPressParser.getParseResult();
			if(null!=differPress&&null!=differPress.getRedirectUrl()){
				return differPressParser.getParseResult();
			}
			DIFFER_PRESS_PARSERS.remove(loginUserToken.getAccount());
		}
		HttpGet request = initRequest(parameter);
		DifferPress differPress = execute(request);
		if(null!=differPress){
			this.dependencys.put(DifferPress.class,differPress);//把parser结果保存到自己的解析器当中,方便下次获取
			DIFFER_PRESS_PARSERS.put(loginUserToken.getAccount(), this);// 保存当前parser解析器
		}
		return differPress;
	}

	public DifferPress execute(HttpGet request) {
		if(hasException()){
			return execClouddiskException();
		}
		UserCheckLoginParser userCheckLoginParser = new UserCheckLoginParser(loginUserToken);
		UserCheckLogin userCheckLogin = userCheckLoginParser.parse();
		if(userCheckLogin.getErrno()!=0){//检测用户是否已经正常使用

			CaptchaParser captchaParser = new CaptchaParser(loginUserToken);
			Captcha captcha = captchaParser.parse();
			if(captchaParser.hasException()){
				this.cloudDiskException = captchaParser.getCloudDiskException();
				return null;
			}
			if (captcha.getCaptchaFlag()) {//登录有验证码操作
				this.captchaParser = captchaParser;
				this.captcha = captcha;
				new Thread(new CaptThread(this)).start();//启动线程进行侦听登录操作
				this.setCloudDiskException(new CloudDiskException("请验证帐户验证码成功后再操作."));
				DifferPress differPress = new DifferPress();
				differPress.setErrno(401);
				differPress.setCddmsg("请验证帐户验证码成功后再操作.");
				return differPress;
			}else{//无验证码,直接登录
				UserTokenParser userTokenParser = new UserTokenParser(loginUserToken);
				UserToken userToken = userTokenParser.parse();

				LoginParameter loginParameter = new LoginParameter();
				LoginParser loginParser = new LoginParser(loginUserToken);
				loginParameter.setToken(userToken.getToken());

				Login login = loginParser.parse(loginParameter);
				if (null == login) {//登录失败
					throw new CloudDiskException("登录失败");
				}else{//登录成功
					this.dataSmooth(loginParser);//数据平滑
				}
			}
		}
		//用户可以正常使用
		httpClient = HttpClients.custom()
				.addInterceptorLast(requestInterceptor)
				.setDefaultCookieStore(readCookieStoreDisk())
				.build();

		RequestConfig differPressConfig = RequestConfig.copy(COOKIE_REQUEST_CONFIG).setCookieSpec(CookieSpecs.NETSCAPE)
				.setRedirectsEnabled(false).build();// use my config
		request.setConfig(differPressConfig);
		try {
			return httpClient.execute(request, responseHandler, httpClientContext);
		} catch (IOException e) {
			e.printStackTrace();
		}
		DifferPress differPress = new DifferPress();
		differPress.setCddmsg(userCheckLogin.getCddmsg());
		differPress.setErrno(userCheckLogin.getErrno());
		differPress.setErrmsg(userCheckLogin.getErrmsg());
		return differPress;
	}

	public Captcha getCaptcha() {
		return captcha;
	}

	public void setCaptcha(Captcha captcha) {
		this.captcha = captcha;
	}

	public CaptchaParser getCaptchaParser() {
		return captchaParser;
	}

	public void setCaptchaParser(CaptchaParser captchaParser) {
		this.captchaParser = captchaParser;
	}
}
