package com.dounine.clouddisk360.parser.deserializer.file.download;

import com.dounine.clouddisk360.parser.FileDownloadFileParser;
import com.dounine.clouddisk360.parser.deserializer.BaseResponseHandle;
import com.dounine.clouddisk360.store.BasePathCommon;
import org.apache.commons.io.FileUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;

import java.io.File;
import java.io.IOException;

public class FileDownloadFileResponseHandle extends BaseResponseHandle<FileDownloadFile, FileDownloadFileParser>
		implements ResponseHandler<FileDownloadFile> {

	public FileDownloadFileResponseHandle(FileDownloadFileParser parse) {
		super(parse);
	}

	@Override
	public FileDownloadFile handleResponse(HttpResponse response) throws ClientProtocolException, IOException {
		HttpEntity entity = response.getEntity();
		File destination = new File(BasePathCommon.BASE_PATH + parse.getLoginUserToken().getAccount() + "/downloads/"
				+ parse.getParameter().getFileName());
		if (!destination.exists()) {
			destination.getParentFile().mkdirs();
		}
		FileUtils.copyInputStreamToFile(entity.getContent(), destination);
		return desializer(destination.getAbsolutePath());
	}

	@Override
	public String disassemblyResult(String result) {
		return String.format("{\"filePath\":\"%s\"}", result).replace("\\", "/");
	}

}
