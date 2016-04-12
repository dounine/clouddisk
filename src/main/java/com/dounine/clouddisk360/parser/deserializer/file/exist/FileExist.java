package com.dounine.clouddisk360.parser.deserializer.file.exist;

import com.alibaba.fastjson.JSON;
import com.dounine.clouddisk360.parser.deserializer.BaseDes;
import com.dounine.clouddisk360.parser.deserializer.file.exist.data.FileData;

public class FileExist extends BaseDes {

	private FileData data;

	public FileData getData() {
		return data;
	}

	public void setData(FileData data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return JSON.toJSONString(this, true);
	}

}
