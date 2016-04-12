package com.dounine.clouddisk360.parser.deserializer.file.exist.data;

import java.util.ArrayList;
import java.util.List;

public class FileData {

	/**
	 * 已存在的文件列表
	 */
	private List<String> exists = new ArrayList<>(0);

	public List<String> getExists() {
		return exists;
	}

	public void setExists(List<String> exists) {
		this.exists = exists;
	}

}
