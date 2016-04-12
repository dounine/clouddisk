package com.dounine.clouddisk360.parser.deserializer.file.list;

import org.apache.http.client.ResponseHandler;

import com.dounine.clouddisk360.parser.FileListParser;
import com.dounine.clouddisk360.parser.deserializer.BaseResponseHandle;

public class FileListResponseHandle extends BaseResponseHandle<FileList, FileListParser> implements ResponseHandler<FileList> {
	
	public FileListResponseHandle(FileListParser parse) {
		super(parse);
	}

}
