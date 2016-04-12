package com.dounine.clouddisk360.parser;

import com.dounine.clouddisk360.annotation.Dependency;
import com.dounine.clouddisk360.annotation.Parse;
import com.dounine.clouddisk360.parser.deserializer.file.create.*;
import com.dounine.clouddisk360.parser.deserializer.login.LoginUserToken;
import org.apache.http.Consts;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;
import java.util.List;

@Parse("文件夹创建")
@Dependency(depends={AuthTokenParser.class})
public class FileCreateParser extends
		BaseParser<HttpPost, FileCreate, FileCreateConst, FileCreateParameter, FileCreateRequestInterceptor, FileCreateResponseHandle, FileCreateParser> {

	public FileCreateParser() {
		super();
	}

	public FileCreateParser(LoginUserToken loginUser) {
		super(loginUser);
	}

	@Override
	public HttpPost initRequest(FileCreateParameter parameter) {
		HttpPost request = new HttpPost(getRequestUri());
		List<NameValuePair> datas = new ArrayList<>(2);
		datas.add(new BasicNameValuePair(CONST.PATH_NAME, parameter.getPath()));
		datas.add(new BasicNameValuePair(CONST.AJAX_KEY, CONST.AJAX_VAL));
		request.setEntity(new UrlEncodedFormEntity(datas, Consts.UTF_8));
		return request;
	}

}
