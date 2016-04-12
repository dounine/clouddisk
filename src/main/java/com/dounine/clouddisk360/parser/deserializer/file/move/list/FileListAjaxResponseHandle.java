package com.dounine.clouddisk360.parser.deserializer.file.move.list;

import org.apache.http.client.ResponseHandler;

import com.dounine.clouddisk360.parser.FileListAjaxParser;
import com.dounine.clouddisk360.parser.deserializer.BaseResponseHandle;

public class FileListAjaxResponseHandle extends BaseResponseHandle<FileListAjax, FileListAjaxParser>
		implements ResponseHandler<FileListAjax> {

	public FileListAjaxResponseHandle(FileListAjaxParser parse) {
		super(parse);
	}

}
