package com.dounine.clouddisk360.parser.deserializer.captcha;

import org.apache.http.client.ResponseHandler;

import com.dounine.clouddisk360.parser.CaptchaParser;
import com.dounine.clouddisk360.parser.deserializer.BaseResponseHandle;

public class CaptchaResponseHandle extends BaseResponseHandle<Captcha, CaptchaParser> implements ResponseHandler<Captcha> {

	public CaptchaResponseHandle(CaptchaParser parse) {
		super(parse);
	}

	@Override
	public Captcha desializer(String result) {
		Captcha captcha = super.desializer(result);
		if(captcha.getCaptchaFlag()){//有验证码
			parse.getHttpClientContext().setAttribute(CaptchaConst.NEED_CAPTCHA, "true");
		}else{
			parse.getHttpClientContext().removeAttribute(CaptchaConst.NEED_CAPTCHA);
		}
		return captcha;
	}

}
