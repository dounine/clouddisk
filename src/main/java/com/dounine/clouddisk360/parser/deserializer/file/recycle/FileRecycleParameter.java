package com.dounine.clouddisk360.parser.deserializer.file.recycle;

import java.util.ArrayList;
import java.util.List;

import com.dounine.clouddisk360.parser.deserializer.BaseParameter;

public class FileRecycleParameter extends BaseParameter{

	private List<String> path = new ArrayList<>(0);

	public List<String> getPath() {
		return path;
	}

	public void setPath(List<String> path) {
		this.path = path;
	}
	

	

}
