package com.dounine.clouddisk360.parser.deserializer.file.upload;

import com.alibaba.fastjson.JSON;
import com.dounine.clouddisk360.parser.deserializer.BaseDes;

public class FileUpload extends BaseDes {

	private FileUploadData data;

	public FileUploadData getData() {
		return data;
	}

	public void setData(FileUploadData data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return JSON.toJSONString(this, true);
	}

}
