package com.dounine.clouddisk360.parser.deserializer.file.history.restore;

import com.dounine.clouddisk360.parser.deserializer.BaseParameter;

public class FileRestoreParameter extends BaseParameter {

	/**
	 * 要穿越版本的文件nid
	 */
	private String nid;
	/**
	 * 穿越到哪个版本中
	 */
	private String id;
	private String source="";

	public String getNid() {
		return nid;
	}

	public void setNid(String nid) {
		this.nid = nid;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

}
