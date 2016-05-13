package com.dounine.clouddisk360.parser.deserializer.file.move.list;

public class FileListAjaxData {

	private String file_path;
	private String nid;
	private String file_name;

	public String getFile_path() {
		return file_path;
	}

	public void setFile_path(String filePath) {
		this.file_path = filePath;
	}

	public String getNid() {
		return nid;
	}

	public void setNid(String nid) {
		this.nid = nid;
	}

	public String getFile_name() {
		return file_name;
	}

	public void setFile_name(String fileName) {
		this.file_name = fileName;
	}

}
