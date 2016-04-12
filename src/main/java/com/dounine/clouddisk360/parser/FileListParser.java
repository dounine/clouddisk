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
	
	private static final Logger LOGGER = LoggerFactory.getLogger(FileListParser.class);

	public FileListParser() {
		super();
	}

	public FileListParser(LoginUserToken loginUser) {
		super(loginUser);
	}

	public HttpPost initRequest(FileListParameter parameter) {
		HttpPost request = new HttpPost(getRequestUri());
		List<NameValuePair> datas = new ArrayList<>(0);
		datas.add(new BasicNameValuePair(CONST.TYPE_NAME, String.valueOf(parameter.getType())));
		datas.add(new BasicNameValuePair(CONST.ORDER_NAME, parameter.getOrder().getValue()));
		datas.add(new BasicNameValuePair(CONST.FIELD_KEY, FileListConst.FIELD_VAL));
		datas.add(new BasicNameValuePair(CONST.PATH_NAME, parameter.getPath()));
		datas.add(new BasicNameValuePair(CONST.PAGE_SIZE_NAME, String.valueOf(parameter.getPage_size())));
		datas.add(new BasicNameValuePair(CONST.AJAX_KEY, CONST.AJAX_VAL));
		request.setEntity(new UrlEncodedFormEntity(datas, Consts.UTF_8));
		return request;
	}

}
