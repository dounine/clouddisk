package com.dounine.clouddisk360.parser.deserializer.file.download.upaddress;

import com.dounine.clouddisk360.parser.FileUploadAddressParser;
import com.dounine.clouddisk360.parser.deserializer.BaseRequestInterceptor;
import org.apache.http.HttpException;
import org.apache.http.HttpRequest;
import org.apache.http.HttpRequestInterceptor;
import org.apache.http.protocol.HttpContext;

import java.io.IOException;

public class FileUploadAddressRequestInterceptor extends BaseRequestInterceptor<FileUploadAddressConst,FileUploadAddressParser>
		implements HttpRequestInterceptor {

	public FileUploadAddressRequestInterceptor(FileUploadAddressParser parser) {
		super(parser);
	}

	@Override
	public void process(HttpRequest request, HttpContext context) throws HttpException, IOException {
		super.process(request, context, true);
	}

}
