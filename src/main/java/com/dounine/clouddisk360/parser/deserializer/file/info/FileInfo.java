package com.dounine.clouddisk360.parser.deserializer.file.info;

import com.alibaba.fastjson.JSON;
import com.dounine.clouddisk360.parser.deserializer.BaseDes;
import com.dounine.clouddisk360.parser.deserializer.file.list.FileListData;

public class FileInfo extends BaseDes {

	private FileListData fileListData;
	
	public FileInfo(){
		fileListData=new FileListData();
	}
	public FileListData getFileListData() {
		return fileListData;
	}
	public void setFileListData(FileListData fileListData) {
		this.fileListData = fileListData;
	}
	@Override
	public String toString() {
		return JSON.toJSONString(this, true);
	}

}
