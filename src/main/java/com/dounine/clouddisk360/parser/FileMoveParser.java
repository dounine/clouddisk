package com.dounine.clouddisk360.parser;

import com.dounine.clouddisk360.annotation.Dependency;
import com.dounine.clouddisk360.annotation.Parse;
import com.dounine.clouddisk360.parser.deserializer.file.move.*;
import com.dounine.clouddisk360.parser.deserializer.login.LoginUserToken;
import org.apache.http.Consts;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;
import java.util.List;

@Parse("文件移动")
@Dependency(depends={AuthTokenParser.class})
public class FileMoveParser extends
		BaseParser<HttpPost, FileMove, FileMoveConst, FileMoveParameter, FileMoveRequestInterceptor, FileMoveResponseHandle, FileMoveParser> {

	public FileMoveParser() {
		super();
	}

	public FileMoveParser(LoginUserToken loginUser) {
		super(loginUser);
	}

	@Override
	public HttpPost initRequest(FileMoveParameter parameter) {
		HttpPost request = new HttpPost(getRequestUri());
		List<NameValuePair> datas = new ArrayList<>(0);
		if (null != parameter.getMoveFiles()) {
			for (String moveFilename : parameter.getMoveFiles()) {
				datas.add(new BasicNameValuePair(CONST.PATH_NAME, moveFilename));
			}
		}
		datas.add(new BasicNameValuePair(CONST.NEWPATH_NAME, parameter.getNewPath()));
		request.setEntity(new UrlEncodedFormEntity(datas, Consts.UTF_8));
		return request;
	}

}
