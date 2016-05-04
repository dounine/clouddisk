package com.dounine.clouddisk360.parser.deserializer.login;

import com.dounine.clouddisk360.parser.LoginParser;
import com.dounine.clouddisk360.parser.deserializer.BaseRequestInterceptor;
import org.apache.http.HttpException;
import org.apache.http.HttpRequest;
import org.apache.http.HttpRequestInterceptor;
import org.apache.http.protocol.HttpContext;

import java.io.IOException;

public class LoginRequestInterceptor extends BaseRequestInterceptor<LoginConst,LoginParser> implements HttpRequestInterceptor {

	public LoginRequestInterceptor(final LoginParser parser) {
		super(parser);
	}

	@Override
	public void process(HttpRequest request, HttpContext context) throws HttpException, IOException {
		super.process(request, context);
		request.setHeader(LoginConst.HOST_KEY, LoginConst.HOST_VAL);
		request.setHeader(LoginConst.ORIGIN_KEY, LoginConst.ORIGIN_VAL);
		request.setHeader(LoginConst.REFERER_KEY, LoginConst.REFERER_VAL);
	}

}
