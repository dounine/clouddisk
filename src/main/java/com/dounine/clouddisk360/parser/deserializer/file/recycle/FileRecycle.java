package com.dounine.clouddisk360.parser.deserializer.file.recycle;

import com.alibaba.fastjson.JSON;
import com.dounine.clouddisk360.parser.deserializer.BaseDes;

public class FileRecycle extends BaseDes {

	private FileRecycleData data;

	public FileRecycleData getData() {
		return data;
	}

	public void setData(FileRecycleData data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return JSON.toJSONString(this, true);
	}

}
