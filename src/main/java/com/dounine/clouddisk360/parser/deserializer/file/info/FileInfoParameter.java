package com.dounine.clouddisk360.parser.deserializer.file.info;

import com.dounine.clouddisk360.parser.deserializer.BaseParameter;
import com.dounine.clouddisk360.parser.deserializer.file.list.FileListParameter;

public class FileInfoParameter extends BaseParameter {

	private String filePath;
	private FileListParameter fileListParameter = new FileListParameter();

	public FileListParameter getFileListParameter() {
		return fileListParameter;
	}

	public void setFileListParameter(FileListParameter fileListParameter) {
		this.fileListParameter = fileListParameter;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

}
