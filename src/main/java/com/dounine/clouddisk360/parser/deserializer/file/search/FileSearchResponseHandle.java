package com.dounine.clouddisk360.parser.deserializer.file.search;

import com.dounine.clouddisk360.parser.FileSearchParser;
import com.dounine.clouddisk360.parser.deserializer.BaseResponseHandle;

public class FileSearchResponseHandle extends BaseResponseHandle<FileSearch, FileSearchParser> {

	public FileSearchResponseHandle(final FileSearchParser parse) {
		super(parse);
	}

}
