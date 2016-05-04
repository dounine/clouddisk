package com.dounine.clouddisk360.parser.deserializer.file.download.upaddress;

import org.apache.http.client.ResponseHandler;

import com.dounine.clouddisk360.parser.FileUploadAddressParser;
import com.dounine.clouddisk360.parser.deserializer.BaseResponseHandle;

public class FileUploadAddressResponseHandle extends BaseResponseHandle<FileUploadAddress, FileUploadAddressParser>
		implements ResponseHandler<FileUploadAddress> {

	public FileUploadAddressResponseHandle(final FileUploadAddressParser parse) {
		super(parse);
	}

}
