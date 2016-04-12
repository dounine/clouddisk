package com.dounine.clouddisk360.parser.deserializer.differpre;

import com.alibaba.fastjson.JSON;
import com.dounine.clouddisk360.parser.deserializer.BaseDes;
import org.apache.http.client.utils.URIBuilder;

public class DifferPress extends BaseDes{

	private URIBuilder redirectUrl;

	public URIBuilder getRedirectUrl() {
		return redirectUrl;
	}

	public void setRedirectUrl(URIBuilder redirectUrl) {
		this.redirectUrl = redirectUrl;
	}

	@Override
	public String toString() {
		return JSON.toJSONString(this, true);
	}

}
