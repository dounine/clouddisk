package com.dounine.clouddisk360.parser.deserializer.file.recycle;

import com.alibaba.fastjson.JSON;

public class FileRecycleData {

	private String task_id;

	public String getTask_id() {
		return task_id;
	}

	public void setTask_id(String task_id) {
		this.task_id = task_id;
	}

	@Override
	public String toString() {
		return JSON.toJSONString(this, true);
	}

}
