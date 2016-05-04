package com.dounine.clouddisk360.parser.deserializer.file.upload.addfile;

import org.apache.http.client.ResponseHandler;

import com.dounine.clouddisk360.parser.FileAddFileParser;
import com.dounine.clouddisk360.parser.deserializer.BaseResponseHandle;

public class FileAddFileResponseHandle extends BaseResponseHandle<FileAddFile, FileAddFileParser>
		implements ResponseHandler<FileAddFile> {

	public FileAddFileResponseHandle(final FileAddFileParser parse) {
		super(parse);
	}

}
