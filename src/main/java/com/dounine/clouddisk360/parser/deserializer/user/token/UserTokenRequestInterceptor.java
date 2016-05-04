package com.dounine.clouddisk360.parser.deserializer.user.token;

import com.dounine.clouddisk360.parser.UserTokenParser;
import com.dounine.clouddisk360.parser.deserializer.BaseRequestInterceptor;
import org.apache.http.HttpException;
import org.apache.http.HttpRequest;
import org.apache.http.protocol.HttpContext;

import java.io.IOException;

public class UserTokenRequestInterceptor extends BaseRequestInterceptor<UserTokenConst,UserTokenParser> {

	public UserTokenRequestInterceptor(final UserTokenParser parser) {
		super(parser);
	}

	@Override
	public void process(final HttpRequest request, final HttpContext context) throws HttpException, IOException {
		super.process(request, context);
		request.addHeader(UserTokenConst.HOST_KEY, UserTokenConst.HOST_VAL);
		request.addHeader(UserTokenConst.REFERER_KEY, UserTokenConst.REFERER_VAL);
	}

}
