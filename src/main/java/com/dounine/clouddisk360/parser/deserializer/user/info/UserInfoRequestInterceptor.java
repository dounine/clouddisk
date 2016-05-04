package com.dounine.clouddisk360.parser.deserializer.user.info;

import com.dounine.clouddisk360.parser.UserInfoParser;
import com.dounine.clouddisk360.parser.deserializer.BaseRequestInterceptor;
import org.apache.http.HttpException;
import org.apache.http.HttpRequest;
import org.apache.http.HttpRequestInterceptor;
import org.apache.http.protocol.HttpContext;

import java.io.IOException;

public class UserInfoRequestInterceptor extends BaseRequestInterceptor<UserInfoConst,UserInfoParser>
		implements HttpRequestInterceptor {

	public UserInfoRequestInterceptor(final UserInfoParser parser) {
		super(parser);
	}

	@Override
	public void process(final HttpRequest request, final HttpContext context) throws HttpException, IOException {
		super.process(request, context,true);
		request.setHeader(UserInfoConst.HOST_KEY,UserInfoConst.HOST_VAL);
	}
}
