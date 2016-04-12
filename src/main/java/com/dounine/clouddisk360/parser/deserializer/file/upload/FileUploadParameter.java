package com.dounine.clouddisk360.parser.deserializer.file.upload;

import java.io.File;

import com.dounine.clouddisk360.parser.deserializer.BaseParameter;

public class FileUploadParameter extends BaseParameter {

	private String Filename;
	private String path;
	private File uploadFile;

	public String getFilename() {
		return Filename;
	}

	public void setFilename(String filename) {
		Filename = filename;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public File getUploadFile() {
		return uploadFile;
	}

	public void setUploadFile(File uploadFile) {
		this.uploadFile = uploadFile;
	}

}
