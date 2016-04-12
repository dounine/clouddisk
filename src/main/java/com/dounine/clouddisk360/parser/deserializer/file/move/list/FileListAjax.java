package com.dounine.clouddisk360.parser.deserializer.file.move.list;

import java.util.List;

import com.alibaba.fastjson.JSON;
import com.dounine.clouddisk360.parser.deserializer.BaseDes;

public class FileListAjax extends BaseDes {

	private String id;
	private List<FileListAjaxData> data;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public List<FileListAjaxData> getData() {
		return data;
	}

	public void setData(List<FileListAjaxData> data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return JSON.toJSONString(this, true);
	}

}
