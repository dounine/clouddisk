package com.dounine.clouddisk360.parser;

import com.dounine.clouddisk360.annotation.Dependency;
import com.dounine.clouddisk360.annotation.Parse;
import com.dounine.clouddisk360.parser.deserializer.file.history.restore.*;
import com.dounine.clouddisk360.parser.deserializer.login.LoginUserToken;
import org.apache.http.Consts;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;
import java.util.List;

@Parse("文件回滚")
@Dependency(depends={AuthTokenParser.class})
public class FileRestoreParser extends
		BaseParser<HttpPost, FileRestore, FileRestoreConst, FileRestoreParameter, FileRestoreRequestInterceptor, FileRestoreResponseHandle, FileRestoreParser> {

	public FileRestoreParser() {
		super();
	}

	public FileRestoreParser(LoginUserToken loginUser) {
		super(loginUser);
	}

	@Override
	public HttpPost initRequest(FileRestoreParameter parameter) {
		HttpPost request = new HttpPost(getRequestUri());
		List<NameValuePair> datas = new ArrayList<>(4);
		datas.add(new BasicNameValuePair(CONST.ID_NAME, parameter.getId()));
		datas.add(new BasicNameValuePair(CONST.NID_NAME, parameter.getNid()));
		datas.add(new BasicNameValuePair(CONST.SOURCE_NAME, parameter.getSource()));
		datas.add(new BasicNameValuePair(CONST.AJAX_KEY, CONST.AJAX_VAL));
		request.setEntity(new UrlEncodedFormEntity(datas, Consts.UTF_8));
		return request;
	}

}
