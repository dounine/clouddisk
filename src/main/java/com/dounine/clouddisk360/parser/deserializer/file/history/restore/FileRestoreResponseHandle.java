package com.dounine.clouddisk360.parser.deserializer.file.history.restore;

import org.apache.http.client.ResponseHandler;

import com.dounine.clouddisk360.parser.FileRestoreParser;
import com.dounine.clouddisk360.parser.deserializer.BaseResponseHandle;

public class FileRestoreResponseHandle extends BaseResponseHandle<FileRestore, FileRestoreParser>
		implements ResponseHandler<FileRestore> {

	public FileRestoreResponseHandle(FileRestoreParser parse) {
		super(parse);
	}

}
