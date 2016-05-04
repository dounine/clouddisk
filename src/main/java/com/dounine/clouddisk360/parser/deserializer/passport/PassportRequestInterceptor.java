package com.dounine.clouddisk360.parser.deserializer.passport;

import com.dounine.clouddisk360.parser.PassportParser;
import com.dounine.clouddisk360.parser.deserializer.BaseRequestInterceptor;
import org.apache.http.HttpException;
import org.apache.http.HttpRequest;
import org.apache.http.HttpRequestInterceptor;
import org.apache.http.protocol.HttpContext;

import java.io.IOException;

public class PassportRequestInterceptor extends BaseRequestInterceptor<PassportConst,PassportParser> implements HttpRequestInterceptor{

	public PassportRequestInterceptor(final PassportParser parser) {
		super(parser);
	}

	@Override
	public void process(final HttpRequest request, final HttpContext context) throws HttpException, IOException {
		super.process(request, context);
		request.setHeader(PassportConst.HOST_KEY, PassportConst.HOST_VAL);
		request.setHeader(PassportConst.REFERER_KEY, PassportConst.REFERER_VAL);
	}

}
