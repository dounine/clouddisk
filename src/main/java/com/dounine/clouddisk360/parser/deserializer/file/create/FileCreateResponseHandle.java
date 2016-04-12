package com.dounine.clouddisk360.parser.deserializer.file.create;

import org.apache.http.client.ResponseHandler;

import com.dounine.clouddisk360.parser.FileCreateParser;
import com.dounine.clouddisk360.parser.deserializer.BaseResponseHandle;

public class FileCreateResponseHandle extends BaseResponseHandle<FileCreate, FileCreateParser> implements ResponseHandler<FileCreate> {

	public FileCreateResponseHandle(FileCreateParser parse) {
		super(parse);
	}

}
