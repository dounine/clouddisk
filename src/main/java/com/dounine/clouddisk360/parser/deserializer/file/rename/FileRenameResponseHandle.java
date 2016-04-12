package com.dounine.clouddisk360.parser.deserializer.file.rename;

import org.apache.http.client.ResponseHandler;

import com.dounine.clouddisk360.parser.FileRenameParser;
import com.dounine.clouddisk360.parser.deserializer.BaseResponseHandle;

public class FileRenameResponseHandle extends BaseResponseHandle<FileRename, FileRenameParser>
		implements ResponseHandler<FileRename> {

	public FileRenameResponseHandle(FileRenameParser parse) {
		super(parse);
	}

}
