package com.dounine.clouddisk360.parser.deserializer.user.check;

import com.dounine.clouddisk360.parser.UserCheckLoginParser;
import com.dounine.clouddisk360.parser.deserializer.BaseRequestInterceptor;
import org.apache.http.HttpException;
import org.apache.http.HttpRequest;
import org.apache.http.HttpRequestInterceptor;
import org.apache.http.protocol.HttpContext;

import java.io.IOException;

public class UserCheckLoginRequestInterceptor extends BaseRequestInterceptor<UserCheckLoginConst,UserCheckLoginParser>
		implements HttpRequestInterceptor {

	public UserCheckLoginRequestInterceptor(UserCheckLoginParser parser) {
		super(parser);
	}

	@Override
	public void process(HttpRequest request, HttpContext context) throws HttpException, IOException {
		super.process(request, context);
	}
}
