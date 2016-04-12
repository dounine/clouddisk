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

	public FileDownloadAddressParser(LoginUserToken loginUser) {
		super(loginUser);
	}

	@Override
	public HttpPost initRequest(FileDownloadAddressParameter parameter) {
		HttpPost request = new HttpPost(getRequestUri());
		List<NameValuePair> datas = new ArrayList<>(3);
		datas.add(new BasicNameValuePair(CONST.NID_NAME, parameter.getNid()));
		datas.add(new BasicNameValuePair(CONST.FNAME_NAME, parameter.getFname()));
		datas.add(new BasicNameValuePair(CONST.AJAX_KEY, CONST.AJAX_VAL));
		request.setEntity(new UrlEncodedFormEntity(datas, Consts.UTF_8));
		return request;
	}

}
