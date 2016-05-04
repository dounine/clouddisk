package com.dounine.clouddisk360.parser.deserializer.file.upload;

import org.apache.http.client.ResponseHandler;

import com.dounine.clouddisk360.parser.FileUploadParser;
import com.dounine.clouddisk360.parser.deserializer.BaseResponseHandle;

public class FileUploadResponseHandle extends BaseResponseHandle<FileUpload, FileUploadParser>
		implements ResponseHandler<FileUpload> {

	public FileUploadResponseHandle(final FileUploadParser parse) {
		super(parse);
	}

}
