package com.dounine.clouddisk360.parser.deserializer.file.download;

import com.alibaba.fastjson.JSON;
import com.dounine.clouddisk360.parser.deserializer.BaseDes;

public class FileDownloadFile extends BaseDes {

	private String filePath;

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	@Override
	public String toString() {
		return JSON.toJSONString(this, true);
	}

}
