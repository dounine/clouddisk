package com.dounine.clouddisk360.parser.deserializer.file.move.list;

import com.dounine.clouddisk360.parser.FileListAjaxParser;
import com.dounine.clouddisk360.parser.deserializer.BaseRequestInterceptor;
import org.apache.http.HttpException;
import org.apache.http.HttpRequest;
import org.apache.http.HttpRequestInterceptor;
import org.apache.http.protocol.HttpContext;

import java.io.IOException;

public class FileListAjaxRequestInterceptor extends BaseRequestInterceptor<FileListAjaxConst,FileListAjaxParser> implements HttpRequestInterceptor{

	public FileListAjaxRequestInterceptor(final FileListAjaxParser parser) {
		super(parser);
	}

	@Override
	public void process(final HttpRequest request, final HttpContext context) throws HttpException, IOException {
		super.process(request, context,true);
	}

}
