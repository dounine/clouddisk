package com.dounine.clouddisk360.parser.deserializer.file.download.upaddress;

import com.alibaba.fastjson.JSON;

public class FileUploadAddressData {

	private String tk;
	private String up;

	public String getTk() {
		return tk;
	}

	public void setTk(String tk) {
		this.tk = tk;
	}

	public String getUp() {
		return up;
	}

	public void setUp(String up) {
		this.up = up;
	}

	@Override
	public String toString() {
		return JSON.toJSONString(this, true);
	}

}
