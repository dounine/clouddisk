package com.dounine.clouddisk360.parser.deserializer.file.move;

import org.apache.http.client.ResponseHandler;

import com.dounine.clouddisk360.parser.FileMoveParser;
import com.dounine.clouddisk360.parser.deserializer.BaseResponseHandle;

public class FileMoveResponseHandle extends BaseResponseHandle<FileMove, FileMoveParser>
		implements ResponseHandler<FileMove> {

	public FileMoveResponseHandle(final FileMoveParser parse) {
		super(parse);
	}

}
