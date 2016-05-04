package com.dounine.clouddisk360.parser;

import com.dounine.clouddisk360.annotation.Dependency;
import com.dounine.clouddisk360.annotation.Parse;
import com.dounine.clouddisk360.parser.deserializer.file.download.dladdress.*;
import com.dounine.clouddisk360.parser.deserializer.login.LoginUserToken;
import org.apache.http.Consts;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;
import java.util.List;

@Parse("获取文件下载地址")
@Dependency(depends={AuthTokenParser.class})
public class FileDownloadAddressParser extends
		BaseParser<HttpPost, FileDownloadAddress, FileDownloadAddressConst, FileDownloadAddressParameter, FileDownloadAddressRequestInterceptor, FileDownloadAddressResponseHandle, FileDownloadAddressParser> {

	public FileDownloadAddressParser() {
		super();
	}

	public FileDownloadAddressParser(final LoginUserToken loginUser) {
		super(loginUser);
	}

	@Override
	public HttpPost initRequest(final FileDownloadAddressParameter parameter) {
		final HttpPost request = new HttpPost(getRequestUri());
		final List<NameValuePair> data = new ArrayList<>(3);
		data.add(new BasicNameValuePair(CONST.NID_NAME, parameter.getNid()));
		data.add(new BasicNameValuePair(CONST.FNAME_NAME, parameter.getFname()));
		data.add(new BasicNameValuePair(CONST.AJAX_KEY, CONST.AJAX_VAL));
		request.setEntity(new UrlEncodedFormEntity(data, Consts.UTF_8));
		return request;
	}

}
