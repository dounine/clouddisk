package com.dounine.clouddisk360.parser.deserializer.captcha;

import com.dounine.clouddisk360.parser.CaptchaParser;
import com.dounine.clouddisk360.parser.deserializer.BaseRequestInterceptor;
import org.apache.http.HttpException;
import org.apache.http.HttpRequest;
import org.apache.http.HttpRequestInterceptor;
import org.apache.http.protocol.HttpContext;

import java.io.IOException;

public class CaptchaRequestInterceptor extends BaseRequestInterceptor<CaptchaConst,CaptchaParser> implements HttpRequestInterceptor {

	public CaptchaRequestInterceptor(CaptchaParser parser) {
		super(parser);
	}

	@Override
	public void process(HttpRequest request, HttpContext context) throws HttpException, IOException {
		super.process(request, context);
		request.setHeader(CaptchaConst.HOST_KEY, CaptchaConst.HOST_VAL);
		request.setHeader(CaptchaConst.REFERER_KEY, CaptchaConst.REFERER_VAL);
	}

}
