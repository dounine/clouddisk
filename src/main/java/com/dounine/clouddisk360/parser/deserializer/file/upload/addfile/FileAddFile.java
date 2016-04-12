package com.dounine.clouddisk360.parser.deserializer.file.upload.addfile;

import com.alibaba.fastjson.JSON;
import com.dounine.clouddisk360.parser.deserializer.BaseDes;

public class FileAddFile extends BaseDes {

	private FileAddFileData data;

	public FileAddFileData getData() {
		return data;
	}

	public void setData(FileAddFileData data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return JSON.toJSONString(this, true);
	}

}
