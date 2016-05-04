package com.dounine.clouddisk360.parser.deserializer.file.history;

import org.apache.http.client.ResponseHandler;

import com.dounine.clouddisk360.parser.FileHistoryParser;
import com.dounine.clouddisk360.parser.deserializer.BaseResponseHandle;

public class FileHistoryResponseHandle extends BaseResponseHandle<FileHistory, FileHistoryParser>
		implements ResponseHandler<FileHistory> {

	public FileHistoryResponseHandle(final FileHistoryParser parse) {
		super(parse);
	}

}
