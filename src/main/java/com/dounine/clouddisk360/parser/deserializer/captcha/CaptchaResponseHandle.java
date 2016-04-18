package com.dounine.clouddisk360.parser.deserializer.captcha;

import org.apache.http.client.ResponseHandler;

import com.dounine.clouddisk360.parser.CaptchaParser;
import com.dounine.clouddisk360.parser.deserializer.BaseResponseHandle;

public class CaptchaResponseHandle extends BaseResponseHandle<Captcha, CaptchaParser> implements ResponseHandler<Captcha> {

	public CaptchaResponseHandle(CaptchaParser parse) {
		super(parse);
	}
}
