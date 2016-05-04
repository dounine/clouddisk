package com.dounine.clouddisk360.parser.deserializer;

import com.dounine.clouddisk360.parser.BaseParser;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpException;
import org.apache.http.HttpRequest;
import org.apache.http.HttpRequestInterceptor;
import org.apache.http.protocol.HttpContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class BaseRequestInterceptor<Const extends BaseConst,Parser extends BaseParser> implements HttpRequestInterceptor {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(BaseRequestInterceptor.class);

	protected Parser parser;
	public BaseRequestInterceptor(final Parser parser){
		this.parser = parser;
	}
	
	public void process(final HttpRequest request, final HttpContext context) throws HttpException, IOException {
		requestInit(request,context);
	}
	
	public void requestInit(final HttpRequest request, final HttpContext context)throws HttpException, IOException {
		request.setHeader(Const.ACCEPT_KEY, Const.ACCEPT_VAL);
		request.setHeader(Const.ACCEPT_ENCODING_KEY, Const.ACCEPT_ENCODING_VAL);
		request.setHeader(Const.ACCEPT_LANGUAGE_KEY, Const.ACCEPT_LANGUAGE_VAL);
		request.setHeader(Const.CONNECTION_KEY, Const.CONNECTION_VAL);
		request.setHeader(Const.CONTENT_TYPE_KEY, Const.CONTENT_TYPE_VAL);
		request.setHeader(Const.USER_AGENT_KEY, Const.USER_AGENT_VAL);
	}
	
	public void process(final HttpRequest request, final HttpContext context,boolean useHost) throws HttpException, IOException {
		requestInit(request,context);
		final String hostName = parser.getRedHost();
		final String host = parser.getRedSchmemHost();
		if(StringUtils.isNotBlank(hostName)){
			request.setHeader(Const.HOST_KEY,hostName);
		}else{
			request.setHeader(Const.HOST_KEY, Const.HOST_VAL);
		}
		if(StringUtils.isNotBlank(host)){
			request.setHeader(Const.REFERER_KEY, host+"/my/index");
			request.setHeader(Const.ORIGIN_KEY, host);
		}else{
			LOGGER.error("Referer 与 Origin 不能为空");
		}
	}

}
