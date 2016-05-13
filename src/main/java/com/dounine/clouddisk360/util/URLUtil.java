package com.dounine.clouddisk360.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class URLUtil {
	private static final Logger LOGGER = LoggerFactory.getLogger(URLUtil.class);
	public final static String decode(final String code){
		try {
			return URLDecoder.decode(code,"utf-8");
		} catch (UnsupportedEncodingException e) {
			LOGGER.error("Error",e);
		}
		return null;
	}
}
