package com.dounine.clouddisk360.parser;

import com.dounine.clouddisk360.annotation.Parse;
import com.dounine.clouddisk360.parser.deserializer.file.upload.addfile.*;
import com.dounine.clouddisk360.parser.deserializer.login.LoginUserToken;
import org.apache.http.Consts;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;
import java.util.List;

@Parse("文件提交事务")
public class FileAddFileParser extends
		BaseParser<HttpPost, FileAddFile, FileAddFileConst, FileAddFileParameter, FileAddFileRequestInterceptor, FileAddFileResponseHandle, FileAddFileParser> {

	public FileAddFileParser() {
		super();
	}

	public FileAddFileParser(LoginUserToken loginUser) {
		super(loginUser);
	}

	@Override
	public HttpPost initRequest(FileAddFileParameter parameter) {
		HttpPost request = new HttpPost(getRequestUri());
		List<NameValuePair> datas = new ArrayList<>(2);
		datas.add(new BasicNameValuePair(CONST.TK_NAME, parameter.getTk()));
		datas.add(new BasicNameValuePair(CONST.AJAX_KEY, CONST.AJAX_VAL));
		request.setEntity(new UrlEncodedFormEntity(datas, Consts.UTF_8));
		return request;
	}

}
