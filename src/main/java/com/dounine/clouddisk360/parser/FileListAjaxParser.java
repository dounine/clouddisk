package com.dounine.clouddisk360.parser;

import com.dounine.clouddisk360.annotation.Dependency;
import com.dounine.clouddisk360.annotation.Parse;
import com.dounine.clouddisk360.parser.deserializer.file.move.list.*;
import com.dounine.clouddisk360.parser.deserializer.login.LoginUserToken;
import org.apache.http.Consts;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;
import java.util.List;

@Parse("文件夹列表树")
@Dependency(depends={AuthTokenParser.class})
public class FileListAjaxParser extends
		BaseParser<HttpPost, FileListAjax, FileListAjaxConst, FileListAjaxParameter, FileListAjaxRequestInterceptor, FileListAjaxResponseHandle, FileListAjaxParser> {

	public FileListAjaxParser() {
		super();
	}

	public FileListAjaxParser(final LoginUserToken loginUser) {
		super(loginUser);
	}

	@Override
	public HttpPost initRequest(final FileListAjaxParameter parameter) {
		final HttpPost request = new HttpPost(getRequestUri());
		final List<NameValuePair> data = new ArrayList<>(4);
		data.add(new BasicNameValuePair(CONST.PATH_NAME, parameter.getPath()));
		data.add(new BasicNameValuePair(CONST.ID_NAME, parameter.getId()));
		data.add(new BasicNameValuePair(CONST.NID_NAME, parameter.getNid()));
		data.add(new BasicNameValuePair(CONST.AJAX_KEY, CONST.AJAX_VAL));
		request.setEntity(new UrlEncodedFormEntity(data, Consts.UTF_8));
		return request;
	}

}
