package com.dounine.clouddisk360.parser.deserializer.user.signin;

import com.dounine.clouddisk360.parser.UserSigninParser;
import com.dounine.clouddisk360.parser.deserializer.BaseRequestInterceptor;
import org.apache.http.HttpException;
import org.apache.http.HttpRequest;
import org.apache.http.HttpRequestInterceptor;
import org.apache.http.protocol.HttpContext;

import java.io.IOException;

public class UserSigninRequestInterceptor extends BaseRequestInterceptor<UserSigninConst,UserSigninParser>
		implements HttpRequestInterceptor {

	public UserSigninRequestInterceptor(UserSigninParser parser) {
		super(parser);
	}

	@Override
	public void process(HttpRequest request, HttpContext context) throws HttpException, IOException {
		super.process(request, context, true);
	}

}
