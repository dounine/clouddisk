package com.dounine.clouddisk360.parser.deserializer.file.move.list;

import com.dounine.clouddisk360.parser.deserializer.BaseParameter;

public class FileListAjaxParameter extends BaseParameter {

	private String path;
	private String id="0";
	private String nid="0";

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNid() {
		return nid;
	}

	public void setNid(String nid) {
		this.nid = nid;
	}

}
