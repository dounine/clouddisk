package com.dounine.clouddisk360.parser.deserializer.authtoken;

import com.dounine.clouddisk360.parser.AuthTokenParser;
import com.dounine.clouddisk360.parser.deserializer.BaseRequestInterceptor;
import org.apache.http.HttpException;
import org.apache.http.HttpRequest;
import org.apache.http.HttpRequestInterceptor;
import org.apache.http.protocol.HttpContext;

import java.io.IOException;

public class AuthTokenRequestInterceptor extends BaseRequestInterceptor<AuthTokenConst,AuthTokenParser>
		implements HttpRequestInterceptor {

	public AuthTokenRequestInterceptor(final AuthTokenParser parser) {
		super(parser);
	}

	@Override
	public void process(final HttpRequest request, final HttpContext context) throws HttpException, IOException {
		super.process(request, context, true);
	}

}
