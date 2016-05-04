package com.dounine.clouddisk360.parser.deserializer.passport;

import com.dounine.clouddisk360.parser.deserializer.BaseParameter;

public class PassportParameter extends BaseParameter {

	private String uri;
	/**
	 * 手动检查
	 */
	private boolean manual;

	public boolean isManual() {
		return manual;
	}

	public void setManual(boolean manual) {
		this.manual = manual;
	}

	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}

}
