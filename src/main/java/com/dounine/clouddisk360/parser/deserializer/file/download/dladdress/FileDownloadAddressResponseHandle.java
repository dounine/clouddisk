package com.dounine.clouddisk360.parser.deserializer.file.download.dladdress;

import org.apache.http.client.ResponseHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dounine.clouddisk360.parser.FileDownloadAddressParser;
import com.dounine.clouddisk360.parser.deserializer.BaseResponseHandle;

public class FileDownloadAddressResponseHandle
		extends BaseResponseHandle<FileDownloadAddress, FileDownloadAddressParser>
		implements ResponseHandler<FileDownloadAddress> {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(FileDownloadAddressResponseHandle.class);

	public FileDownloadAddressResponseHandle(FileDownloadAddressParser parse) {
		super(parse);
	}

	@Override
	public FileDownloadAddress desializer(String result) {
		FileDownloadAddress fileDownloadAddress = super.desializer(result);
		if(fileDownloadAddress.getErrno()!=0){
			LOGGER.error(fileDownloadAddress.getErrmsg());
		}
		return fileDownloadAddress;
	}
	

}
