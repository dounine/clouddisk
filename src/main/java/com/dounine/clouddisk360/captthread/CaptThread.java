package com.dounine.clouddisk360.captthread;

import com.dounine.clouddisk360.parser.DifferPressParser;
import com.dounine.clouddisk360.parser.LoginParser;
import com.dounine.clouddisk360.parser.PassportParser;
import com.dounine.clouddisk360.parser.UserTokenParser;
import com.dounine.clouddisk360.parser.deserializer.login.Login;
import com.dounine.clouddisk360.parser.deserializer.login.LoginParameter;
import com.dounine.clouddisk360.parser.deserializer.passport.Passport;
import com.dounine.clouddisk360.parser.deserializer.passport.PassportParameter;
import com.dounine.clouddisk360.parser.deserializer.user.token.UserToken;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class CaptThread implements Runnable{

	private static final Logger LOGGER = LoggerFactory.getLogger(CaptThread.class);

	private static Map<String,PassportParser> PASSPORT_PARSER_MAP = new HashMap<>();

	private DifferPressParser differPressParser;
	private LoginParser loginParser;
	private PassportParser passportParser;
	public CaptThread(DifferPressParser differPressParser){
		LOGGER.info("云盘线程验证启动");
		this.differPressParser = differPressParser;
		init();
	}

	private void init(){
		loginParser = new LoginParser(differPressParser.getLoginUserToken());
		loginParser.dataSmooth(differPressParser.getCaptchaParser());

		passportParser = new PassportParser(differPressParser.getLoginUserToken());
		PassportParameter passportParameter = new PassportParameter();
		passportParameter.setUri(differPressParser.getCaptcha().getCaptchaUrl());
		Passport passport = passportParser.parse(passportParameter);

		String account = differPressParser.getLoginUserToken().getAccount();
		CaptchaValidator accountValidator = new CaptchaValidator();
		accountValidator.setAddTime(LocalDateTime.now());
		accountValidator.setCaptFilePath(passport.getCaptchaUrl());//验证码文件路径
		CaptchaThreadValidator.addCaptchaValidator(account, accountValidator);// add
	}

	@Override
	public void run() {
		// validator
		boolean returnExist = true;
		CaptchaValidator cValidator = null;
		String account = differPressParser.getLoginUserToken().getAccount();
		try {
			int timeOutSeconds = CaptchaThreadValidator.timeoutMin;
			int seconds = 3;
			int runTimeSeconds = 0;
			int sleepTimeSeconds = 1000*seconds;
			while (returnExist) {
				Thread.sleep(sleepTimeSeconds);
				runTimeSeconds += seconds;
				cValidator = CaptchaThreadValidator.getCaptchaValidator(account);
				if (null != cValidator && null != cValidator.getCaptchaValue()) {
					returnExist = false;
				} else if (null == cValidator) {
					returnExist = false;
				}
				if (runTimeSeconds >= timeOutSeconds) {
					returnExist = false;
					CaptchaThreadValidator.removeCaptchaValidator(account);
				}
				PassportParser _passportParser = get_passport(account);//获取手动变动的验证码解析器
				if(null!=_passportParser){
					passportParser = _passportParser;
				}
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		if (null == cValidator) {
			CaptchaThreadValidator.removeCaptchaValidator(account);
			remove_passport(account);//移除验证码解析器
			LOGGER.info("验证码超时未处理,线程监听验证结束");
		}else{
			LoginParameter loginParameter = new LoginParameter();
			loginParameter.setCaptchaValue(cValidator.getCaptchaValue());
			loginParser.dataSmooth(passportParser);// 数据平滑

			UserTokenParser userTokenParser = new UserTokenParser(differPressParser.getLoginUserToken());
			userTokenParser.dataSmooth(loginParser);// 数据平滑
			UserToken userToken = userTokenParser.parse();
			loginParameter.setToken(userToken.getToken());

			Login login = loginParser.parse(loginParameter);
			CaptchaThreadValidator.emptyCaptchaValue(account);//清空验证码
			if (null != login&& StringUtils.isNotBlank(login.getQid())) {
				CaptchaThreadValidator.updateValidMsgAndTime(account,"登录成功",true);//更新验证时间
				remove_passport(account);//移除验证码解析器
				LOGGER.info("线程登录成功");
			}else if(null==login||login.getErrno()!=0){
				CaptchaThreadValidator.updateValidMsgAndTime(account,login.getErrmsg(),false);//更新验证时间
				new Thread(new CaptThread(differPressParser)).start();//启动线程进行侦听登录操作
				LOGGER.info(login.getErrmsg()+",云盘线程重新监听");
			}else{
				LOGGER.info("登录线程线束");
			}
		}
	}

	public static void push_passport(String account,PassportParser passportParser){
		synchronized (PASSPORT_PARSER_MAP){
			PASSPORT_PARSER_MAP.put(account,passportParser);
		}
	}

	public PassportParser remove_passport(String account){
		synchronized (PASSPORT_PARSER_MAP){
			return PASSPORT_PARSER_MAP.remove(account);
		}
	}

	public PassportParser get_passport(String account){
		synchronized (PASSPORT_PARSER_MAP){
			return PASSPORT_PARSER_MAP.get(account);
		}
	}
}
