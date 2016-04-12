package com.dounine.clouddisk360.parser;

import com.dounine.clouddisk360.annotation.Dependency;
import com.dounine.clouddisk360.annotation.Parse;
import com.dounine.clouddisk360.parser.deserializer.file.history.*;
import com.dounine.clouddisk360.parser.deserializer.login.LoginUserToken;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.Consts;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;
import java.util.List;

@Parse("文件历史版本")
@Dependency(depends={AuthTokenParser.class})
public class FileHistoryParser extends
		BaseParser<HttpPost, FileHistory, FileHistoryConst, FileHistoryParameter, FileHistoryRequestInterceptor, FileHistoryResponseHandle, FileHistoryParser> {

	public FileHistoryParser() {
		super();
	}

	public FileHistoryParser(LoginUserToken loginUser) {
		super(loginUser);
	}

	@Override
	public HttpPost initRequest(FileHistoryParameter parameter) {
		HttpPost request = new HttpPost(getRequestUri());
		List<NameValuePair> datas = new ArrayList<>(6);
		datas.add(new BasicNameValuePair(CONST.NID_NAME, parameter.getNid()));
		datas.add(new BasicNameValuePair(CONST.HIS_NID_NAME, parameter.getHis_nid()));
		datas.add(new BasicNameValuePair(CONST.START_NAME, parameter.getStart()));
		datas.add(new BasicNameValuePair(CONST.NUM_NAME, parameter.getNum()));
		datas.add(new BasicNameValuePair(CONST.SOURCE_NAME, StringUtils.EMPTY));
		datas.add(new BasicNameValuePair(CONST.AJAX_KEY, CONST.AJAX_VAL));
		request.setEntity(new UrlEncodedFormEntity(datas, Consts.UTF_8));
		return request;
	}

}
