package com.dounine.clouddisk360.parser;

import com.dounine.clouddisk360.annotation.Dependency;
import com.dounine.clouddisk360.annotation.Parse;
import com.dounine.clouddisk360.parser.deserializer.file.list.*;
import com.dounine.clouddisk360.parser.deserializer.login.LoginUserToken;
import org.apache.http.Consts;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.message.BasicNameValuePair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

@Parse("文件列表信息")
@Dependency(depends = { AuthTokenParser.class })
public class FileListParser extends
		BaseParser<HttpPost, FileList, FileListConst, FileListParameter, FileListRequestInterceptor, FileListResponseHandle, FileListParser> {
	
	public FileListParser() {
		super();
	}

	public FileListParser(final LoginUserToken loginUser) {
		super(loginUser);
	}

	public HttpPost initRequest(final FileListParameter parameter) {
		final HttpPost request = new HttpPost(getRequestUri());
		final List<NameValuePair> data = new ArrayList<>(0);
		data.add(new BasicNameValuePair(CONST.TYPE_NAME, String.valueOf(parameter.getType())));
		data.add(new BasicNameValuePair(CONST.ORDER_NAME, parameter.getOrder().getValue()));
		data.add(new BasicNameValuePair(CONST.FIELD_KEY, FileListConst.FIELD_VAL));
		data.add(new BasicNameValuePair(CONST.PATH_NAME, parameter.getPath()));
		data.add(new BasicNameValuePair(CONST.PAGE_SIZE_NAME, String.valueOf(parameter.getPage_size())));
		data.add(new BasicNameValuePair(CONST.AJAX_KEY, CONST.AJAX_VAL));
		request.setEntity(new UrlEncodedFormEntity(data, Consts.UTF_8));
		return request;
	}

}
