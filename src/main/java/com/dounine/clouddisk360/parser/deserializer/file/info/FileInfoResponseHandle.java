package com.dounine.clouddisk360.parser.deserializer.file.info;

import org.apache.http.client.ResponseHandler;

import com.dounine.clouddisk360.parser.FileInfoParser;
import com.dounine.clouddisk360.parser.deserializer.BaseResponseHandle;

public class FileInfoResponseHandle extends BaseResponseHandle<FileInfo, FileInfoParser>
		implements ResponseHandler<FileInfo> {

	public FileInfoResponseHandle(final FileInfoParser parse) {
		super(parse);
	}

}
