package com.dounine.clouddisk360.parser.deserializer.file.recycle;

import org.apache.http.client.ResponseHandler;

import com.dounine.clouddisk360.parser.FileRecycleParser;
import com.dounine.clouddisk360.parser.deserializer.BaseResponseHandle;

public class FileRecycleResponseHandle extends BaseResponseHandle<FileRecycle, FileRecycleParser> implements ResponseHandler<FileRecycle> {

	public FileRecycleResponseHandle(FileRecycleParser parse) {
		super(parse);
	}


}
