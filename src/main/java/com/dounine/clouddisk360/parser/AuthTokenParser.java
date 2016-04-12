package com.dounine.clouddisk360.parser;

import com.dounine.clouddisk360.annotation.DependResult;
import com.dounine.clouddisk360.annotation.Dependency;
import com.dounine.clouddisk360.annotation.Parse;
import com.dounine.clouddisk360.exception.CloudDiskException;
import com.dounine.clouddisk360.parser.deserializer.authtoken.*;
import com.dounine.clouddisk360.parser.deserializer.differpre.DifferPress;
import com.dounine.clouddisk360.parser.deserializer.login.Login;
import com.dounine.clouddisk360.parser.deserializer.login.LoginConst;
import com.dounine.clouddisk360.parser.deserializer.login.LoginUserToken;
import org.apache.http.client.methods.HttpGet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URISyntaxException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Parse("授权令牌")
@Dependency(depends = { DifferPressParser.class })
@DependResult(customInit = false, result = AuthToken.class)
public class AuthTokenParser extends
		BaseParser<HttpGet, AuthToken, AuthTokenConst, AuthTokenParameter, AuthTokenRequestInterceptor, AuthTokenResponseHandle, AuthTokenParser> {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(AuthTokenParser.class);

	public static Map<String, AuthTokenParser> authTokenParsers = new HashMap<>();

	public AuthTokenParser() {
		super();
	}

	public AuthTokenParser(LoginUserToken loginUser) {
		super(loginUser);
	}

	@Override
	public HttpGet initRequest(AuthTokenParameter parameter) {
		DifferPress differPress = this.getDependResult(DifferPress.class);
		if(null==differPress){
			throw new CloudDiskException("differPress 中的host读取不到");
		}else{
			try {
				return new HttpGet(differPress.getRedirectUrl().build());
			} catch (URISyntaxException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	@Override
	public AuthToken parse() {
		AuthToken authToken = null;
		if(hasException()){
			return execClouddiskException();
		}
		AuthTokenParser authTokenParser = authTokenParsers.get(loginUserToken.getAccount());
		if (null != authTokenParser) {
			if(authTokenParser.getCreateDateTime().plusDays(1).isAfter(LocalDateTime.now())){
				this.dataSmooth(authTokenParser);
				authToken = authTokenParser.getParseResult();
				if(null!=authToken){
					return authToken;
				}
			}
		}
		authToken = super.parse();
		if(hasException()){
			return execClouddiskException();
		}
		if(null!=authToken){
			Login login = getLoginInfo();
			if(null==login){
				setBianaryFilename(LoginConst.USER_INFO_PATH_NAME);
				login = readObjForDisk(Login.class);
				setLoginInfo(login);//把用户信息放到上下文当中,以便随时获取
			}
			LOGGER.info("更新authToken解析器了");
			dependencys.put(AuthToken.class,authToken);
			authTokenParsers.put(loginUserToken.getAccount(), this);// 保存当前parser解析器
		}else{
			cloudDiskException = new CloudDiskException("获取authToke授权为空");
		}
		return authToken;
	}

}
