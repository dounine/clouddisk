package com.dounine.clouddisk360.parser;

import com.dounine.clouddisk360.annotation.DependResult;
import com.dounine.clouddisk360.annotation.Dependency;
import com.dounine.clouddisk360.annotation.Parse;
import com.dounine.clouddisk360.parser.deserializer.file.exist.*;
import com.dounine.clouddisk360.parser.deserializer.login.LoginUserToken;
import org.apache.http.Consts;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Parse("判断文件是否存在")
@Dependency(depends = { AuthTokenParser.class })
@DependResult(customInit = false, result = FileExist.class)
public class FileExistParser extends
		BaseParser<HttpPost, FileExist, FileExistConst, FileExistParameter, FileExistRequestInterceptor, FileExistResponseHandle, FileExistParser> {

	public FileExistParser() {
		super();
	}

	public FileExistParser(final LoginUserToken loginUser) {
		super(loginUser);
	}

	@Override
	public HttpPost initRequest(final FileExistParameter parameter) {
		final HttpPost request = new HttpPost(getRequestUri());
		final List<NameValuePair> data = new ArrayList<>(0);
		data.add(new BasicNameValuePair(CONST.DIR_NAME, parameter.getDir()));
		data.addAll(parameter.getFnames().stream().map(fame -> new BasicNameValuePair(CONST.FNAME_NAME, fame)).collect(Collectors.toList()));
		data.add(new BasicNameValuePair(CONST.AJAX_KEY, CONST.AJAX_VAL));
		request.setEntity(new UrlEncodedFormEntity(data, Consts.UTF_8));
		return request;
	}

}
