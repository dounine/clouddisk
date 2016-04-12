package com.dounine.clouddisk360.parser;

import com.dounine.clouddisk360.annotation.Dependency;
import com.dounine.clouddisk360.annotation.Parse;
import com.dounine.clouddisk360.parser.deserializer.login.LoginUserToken;
import com.dounine.clouddisk360.parser.deserializer.user.signin.*;
import com.dounine.clouddisk360.parser.deserializer.user.size.UserSizeConst;
import com.dounine.clouddisk360.util.TimeUtil;
import org.apache.http.Consts;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;
import java.util.List;

@Parse("用户签到[抽奖空间]")
@Dependency(depends={AuthTokenParser.class})
public class UserSigninParser extends
		BaseParser<HttpPost, UserSignin, UserSigninConst, UserSigninParameter, UserSigninRequestInterceptor, UserSigninResponseHandle,UserSigninParser> {

	public UserSigninParser(){
		super();
	}
	public UserSigninParser(LoginUserToken loginUser) {
		super(loginUser);
	}

	@Override
	public HttpPost initRequest(UserSigninParameter parameter) {
		HttpPost request = new HttpPost(getRequestUri());
		List<NameValuePair> datas = new ArrayList<>(4);
		datas.add(new BasicNameValuePair(CONST.AJAX_KEY, CONST.AJAX_VAL));
		datas.add(new BasicNameValuePair(CONST.METHOD_KEY, UserSizeConst.METHOD_VAL));
		datas.add(new BasicNameValuePair("t", TimeUtil.getTimeLenth(10)));
		request.setEntity(new UrlEncodedFormEntity(datas, Consts.UTF_8));
		return request;
	}

}
