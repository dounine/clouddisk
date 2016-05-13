package com.dounine.clouddisk360.parser.deserializer.file.search;

import com.dounine.clouddisk360.parser.deserializer.BaseParameter;

public class FileSearchParameter extends BaseParameter{

	private String key;
	private Integer page;
	private Integer page_size;

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public Integer getPage_size() {
		return page_size;
	}

	public void setPage_size(Integer pageSize) {
		this.page_size = pageSize;
	}

}
