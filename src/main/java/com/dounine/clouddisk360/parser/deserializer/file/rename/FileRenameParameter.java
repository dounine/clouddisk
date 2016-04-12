package com.dounine.clouddisk360.parser.deserializer.file.rename;

import com.dounine.clouddisk360.parser.deserializer.BaseParameter;

public class FileRenameParameter extends BaseParameter{

	/*
	 * 要修改的目录路径
	 */
	private String path;
	/**
	 * 文件nid
	 */
	private String nid;
	/**
	 * 新文件名
	 */
	private String newpath;
	
	

	public String getNid() {
		return nid;
	}

	public void setNid(String nid) {
		this.nid = nid;
	}

	public String getNewpath() {
		return newpath;
	}

	public void setNewpath(String newpath) {
		this.newpath = newpath;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

}
