package com.dounine.clouddisk360.parser.deserializer.file.download;

import com.dounine.clouddisk360.parser.deserializer.BaseParameter;

public class FileDownloadFileParameter extends BaseParameter{

	private String downloadPath;
	private String fileName;

	public String getDownloadPath() {
		return downloadPath;
	}

	public void setDownloadPath(String downloadPath) {
		this.downloadPath = downloadPath;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

}
