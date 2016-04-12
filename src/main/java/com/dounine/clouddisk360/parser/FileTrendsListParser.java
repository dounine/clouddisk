package com.dounine.clouddisk360.parser;

import org.apache.http.client.methods.HttpPost;

import com.dounine.clouddisk360.annotation.Dependency;
import com.dounine.clouddisk360.annotation.Parse;
import com.dounine.clouddisk360.parser.deserializer.file.trends.FileTrendsList;
import com.dounine.clouddisk360.parser.deserializer.file.trends.FileTrendsListConst;
import com.dounine.clouddisk360.parser.deserializer.file.trends.FileTrendsListParameter;
import com.dounine.clouddisk360.parser.deserializer.file.trends.FileTrendsListRequestInterceptor;
import com.dounine.clouddisk360.parser.deserializer.file.trends.FileTrendsListResponseHandle;
import com.dounine.clouddisk360.parser.deserializer.login.LoginUserToken;

@Parse("操作历史")
@Dependency(depends={AuthTokenParser.class})
public class FileTrendsListParser extends
		BaseParser<HttpPost, FileTrendsList, FileTrendsListConst, FileTrendsListParameter, FileTrendsListRequestInterceptor, FileTrendsListResponseHandle, FileTrendsListParser> {

	public FileTrendsListParser() {
		super();
	}

	public FileTrendsListParser(LoginUserToken loginUser) {
		super(loginUser);
	}

	@Override
	public String getUriPath() {
		return CONST.URI_PATH;
	}
}
