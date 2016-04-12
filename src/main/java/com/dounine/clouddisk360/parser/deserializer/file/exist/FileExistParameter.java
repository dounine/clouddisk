package com.dounine.clouddisk360.parser.deserializer.file.exist;

import java.util.ArrayList;
import java.util.List;

import com.dounine.clouddisk360.parser.deserializer.BaseParameter;

public class FileExistParameter extends BaseParameter {

	/**
	 * 检查文件的路径
	 */
	private String dir = "/";
	/**
	 * 检查文件列表
	 */
	private List<String> fnames = new ArrayList<>(0);

	public String getDir() {
		return dir;
	}

	public void setDir(String dir) {
		this.dir = dir;
	}

	public List<String> getFnames() {
		return fnames;
	}

	public void setFnames(List<String> fnames) {
		this.fnames = fnames;
	}

}
