package com.dounine.clouddisk360.parser.deserializer.file.search;

import com.alibaba.fastjson.JSON;
import com.dounine.clouddisk360.parser.deserializer.BaseDes;

public class FileSearch extends BaseDes {

	private FileSearchData data;

	public FileSearchData getData() {
		return data;
	}

	public void setData(FileSearchData data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return JSON.toJSONString(this, true);
	}

}
