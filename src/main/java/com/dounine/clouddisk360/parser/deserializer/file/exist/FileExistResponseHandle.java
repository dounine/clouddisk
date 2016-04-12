package com.dounine.clouddisk360.parser.deserializer.file.exist;

import org.apache.http.client.ResponseHandler;

import com.dounine.clouddisk360.parser.FileExistParser;
import com.dounine.clouddisk360.parser.deserializer.BaseResponseHandle;

public class FileExistResponseHandle extends BaseResponseHandle<FileExist, FileExistParser>
		implements ResponseHandler<FileExist> {

	public FileExistResponseHandle(FileExistParser parse) {
		super(parse);
	}

}
