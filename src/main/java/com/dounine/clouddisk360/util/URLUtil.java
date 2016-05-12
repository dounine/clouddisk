package com.dounine.clouddisk360.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

public final class URLUtil {

	private URLUtil(){}
	
	public final static String decode(final String code){
		try {
			return URLDecoder.decode(code,"utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return null;
	}
}
