package com.dounine.clouddisk360.parser.deserializer.file.history;

import com.alibaba.fastjson.JSON;
import com.dounine.clouddisk360.parser.deserializer.BaseDes;
import com.dounine.clouddisk360.parser.deserializer.file.history.data.FileHistoryData;

public class FileHistory extends BaseDes {

	private FileHistoryData data;

	public FileHistoryData getData() {
		return data;
	}

	public void setData(FileHistoryData data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return JSON.toJSONString(this, true);
	}

}
