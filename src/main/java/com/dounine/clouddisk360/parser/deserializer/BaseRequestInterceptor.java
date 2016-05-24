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

public class BaseRequestInterceptor<C  extends BaseConst,Parser extends BaseParser> implements HttpRequestInterceptor {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(BaseRequestInterceptor.class);

	protected Parser parser;
	public BaseRequestInterceptor(final Parser parser){
		this.parser = parser;
	}
	
	public void process(final HttpRequest request, final HttpContext context) throws HttpException, IOException {
		requestInit(request,context);
	}
	
	public void requestInit(final HttpRequest request, final HttpContext context) throws HttpException, IOException {
		try{
			request.setHeader(C.ACCEPT_KEY, C.ACCEPT_VAL);
			request.setHeader(C.ACCEPT_ENCODING_KEY, C.ACCEPT_ENCODING_VAL);
			request.setHeader(C.ACCEPT_LANGUAGE_KEY, C.ACCEPT_LANGUAGE_VAL);
			request.setHeader(C.CONNECTION_KEY, C.CONNECTION_VAL);
			request.setHeader(C.CONTENT_TYPE_KEY, C.CONTENT_TYPE_VAL);
			request.setHeader(C.USER_AGENT_KEY, C.USER_AGENT_VAL);
		}
		catch (Exception ex) { 
			LOGGER.error("RequestContext",ex);
		    throw ex;
		}
		
	}
	
	public void process(final HttpRequest request, final HttpContext context,boolean useHost) throws HttpException, IOException {
		try{
			requestInit(request,context);
			final String hostName = parser.getRedHost();
			final String host = parser.getRedSchmemHost();
			if(StringUtils.isNotBlank(hostName)){
				request.setHeader(C.HOST_KEY,hostName);
			}else{
				request.setHeader(C.HOST_KEY, C.HOST_VAL);
			}
			if(StringUtils.isNotBlank(host)){
				request.setHeader(C.REFERER_KEY, host+"/my/index");
				request.setHeader(C.ORIGIN_KEY, host);
			}else{
				LOGGER.error("Referer 与 Origin 不能为空");
			}
		}
		catch(Exception ex){
			LOGGER.error("ProcessContext",ex);
		    throw ex;
		}
		
	}

}
