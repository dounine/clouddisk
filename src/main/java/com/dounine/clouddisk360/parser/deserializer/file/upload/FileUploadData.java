package com.dounine.clouddisk360.parser.deserializer.file.upload;

import com.alibaba.fastjson.JSON;

public class FileUploadData {

	private Integer autoCommit;
	private String tk;
	private String etk;

	public Integer getAutoCommit() {
		return autoCommit;
	}

	public void setAutoCommit(Integer autoCommit) {
		this.autoCommit = autoCommit;
	}

	public String getTk() {
		return tk;
	}

	public void setTk(String tk) {
		this.tk = tk;
	}

	public String getEtk() {
		return etk;
	}

	public void setEtk(String etk) {
		this.etk = etk;
	}

	@Override
	public String toString() {
		return JSON.toJSONString(this, true);
	}

}
