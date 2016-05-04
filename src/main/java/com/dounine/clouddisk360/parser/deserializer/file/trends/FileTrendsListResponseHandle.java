package com.dounine.clouddisk360.parser.deserializer.file.trends;

import org.apache.http.client.ResponseHandler;

import com.dounine.clouddisk360.parser.FileTrendsListParser;
import com.dounine.clouddisk360.parser.deserializer.BaseResponseHandle;

public class FileTrendsListResponseHandle extends BaseResponseHandle<FileTrendsList, FileTrendsListParser>
		implements ResponseHandler<FileTrendsList> {

	public FileTrendsListResponseHandle(final FileTrendsListParser parse) {
		super(parse);
	}

}
