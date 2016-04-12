package com.dounine.clouddisk360.parser;

import com.dounine.clouddisk360.annotation.DependResult;
import com.dounine.clouddisk360.annotation.Dependency;
import com.dounine.clouddisk360.annotation.Parse;
import com.dounine.clouddisk360.parser.deserializer.file.download.upaddress.*;
import com.dounine.clouddisk360.parser.deserializer.login.LoginUserToken;
import org.apache.http.Consts;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;
import java.util.List;

@Parse("获取文件上传地址")
@Dependency(depends={AuthTokenParser.class})
@DependResult(customInit=false,result=FileUploadAddress.class)
public class FileUploadAddressParser extends
		BaseParser<HttpPost, FileUploadAddress, FileUploadAddressConst, FileUploadAddressParameter, FileUploadAddressRequestInterceptor, FileUploadAddressResponseHandle, FileUploadAddressParser> {

	public FileUploadAddressParser() {
		super();
	}

	public FileUploadAddressParser(LoginUserToken loginUser) {
		super(loginUser);
	}

	public HttpPost initRequest(FileUploadAddressParameter parameter) {
		HttpPost request = new HttpPost(getRequestUri());
		List<NameValuePair> datas = new ArrayList<>(1);
		datas.add(new BasicNameValuePair(CONST.AJAX_KEY, CONST.AJAX_VAL));
		request.setEntity(new UrlEncodedFormEntity(datas, Consts.UTF_8));
		return request;
	}

}
