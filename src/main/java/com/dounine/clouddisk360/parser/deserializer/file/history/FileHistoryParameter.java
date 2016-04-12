package com.dounine.clouddisk360.parser.deserializer.file.history;

import com.dounine.clouddisk360.parser.deserializer.BaseParameter;

public class FileHistoryParameter extends BaseParameter {

	private String nid;
	private String his_nid;
	private String start;
	private String num;
	private String source;

	public String getNid() {
		return nid;
	}

	public void setNid(String nid) {
		this.nid = nid;
	}

	public String getHis_nid() {
		return his_nid;
	}

	public void setHis_nid(String his_nid) {
		this.his_nid = his_nid;
	}

	public String getStart() {
		return start;
	}

	public void setStart(String start) {
		this.start = start;
	}

	public String getNum() {
		return num;
	}

	public void setNum(String num) {
		this.num = num;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

}
