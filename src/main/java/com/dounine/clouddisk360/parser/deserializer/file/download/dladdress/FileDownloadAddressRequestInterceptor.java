package com.dounine.clouddisk360.parser.deserializer.file.download.dladdress;

import com.dounine.clouddisk360.parser.FileDownloadAddressParser;
import com.dounine.clouddisk360.parser.deserializer.BaseRequestInterceptor;
import org.apache.http.HttpException;
import org.apache.http.HttpRequest;
import org.apache.http.HttpRequestInterceptor;
import org.apache.http.protocol.HttpContext;

import java.io.IOException;

public class FileDownloadAddressRequestInterceptor extends BaseRequestInterceptor<FileDownloadAddressConst,FileDownloadAddressParser>
		implements HttpRequestInterceptor {

	public FileDownloadAddressRequestInterceptor(FileDownloadAddressParser parser) {
		super(parser);
	}

	@Override
	public void process(HttpRequest request, HttpContext context) throws HttpException, IOException {
		super.process(request, context, true);
	}

}
