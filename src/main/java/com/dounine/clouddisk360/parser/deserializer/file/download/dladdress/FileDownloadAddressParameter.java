package com.dounine.clouddisk360.parser.deserializer.file.download.dladdress;

import com.dounine.clouddisk360.parser.deserializer.BaseParameter;

public class FileDownloadAddressParameter extends BaseParameter{

	private String fname;
	private String nid;

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public String getNid() {
		return nid;
	}

	public void setNid(String nid) {
		this.nid = nid;
	}

}
