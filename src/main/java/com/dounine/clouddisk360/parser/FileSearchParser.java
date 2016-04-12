package com.dounine.clouddisk360.parser;

import com.dounine.clouddisk360.annotation.Dependency;
import com.dounine.clouddisk360.annotation.Parse;
import com.dounine.clouddisk360.parser.deserializer.file.search.*;
import com.dounine.clouddisk360.parser.deserializer.login.LoginUserToken;
import org.apache.http.Consts;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;
import java.util.List;

@Parse("文件搜索")
@Dependency(depends={AuthTokenParser.class})
public class FileSearchParser extends
		BaseParser<HttpPost, FileSearch, FileSearchConst, FileSearchParameter, FileSearchRequestInterceptor, FileSearchResponseHandle, FileSearchParser> {

	public FileSearchParser() {
		super();
	}

	public FileSearchParser(LoginUserToken loginUser) {
		super(loginUser);
	}

	@Override
	public HttpPost initRequest(FileSearchParameter parameter) {
		HttpPost request = new HttpPost(getRequestUri());
		List<NameValuePair> datas = new ArrayList<>(5);
		datas.add(new BasicNameValuePair(CONST.KEY_NAME, parameter.getKey()));
		datas.add(new BasicNameValuePair(CONST.ISFPATH_NAME, "1"));
		datas.add(new BasicNameValuePair(CONST.PAGE_NAME, String.valueOf(parameter.getPage())));
		datas.add(new BasicNameValuePair(CONST.PAGE_SIZE_NAME, String.valueOf(parameter.getPage_size())));
		datas.add(new BasicNameValuePair(CONST.AJAX_KEY, CONST.AJAX_VAL));
		request.setEntity(new UrlEncodedFormEntity(datas, Consts.UTF_8));
		return request;
	}

}
