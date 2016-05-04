package com.dounine.clouddisk360.parser;

import com.dounine.clouddisk360.annotation.Dependency;
import com.dounine.clouddisk360.annotation.Parse;
import com.dounine.clouddisk360.parser.deserializer.file.recycle.*;
import com.dounine.clouddisk360.parser.deserializer.login.LoginUserToken;
import org.apache.http.Consts;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Parse("文件回收")
@Dependency(depends={AuthTokenParser.class})
public class FileRecycleParser extends
		BaseParser<HttpPost, FileRecycle, FileRecycleConst, FileRecycleParameter, FileRecycleRequestInterceptor, FileRecycleResponseHandle, FileRecycleParser> {

	public FileRecycleParser() {
		super();
	}

	public FileRecycleParser(final LoginUserToken loginUser) {
		super(loginUser);
	}

	@Override
	public HttpPost initRequest(final FileRecycleParameter parameter) {
		final HttpPost request = new HttpPost(getRequestUri());
		final List<NameValuePair> data = new ArrayList<>(0);
		if (null != parameter.getPath()) {
			data.addAll(parameter.getPath().stream().map(pa -> new BasicNameValuePair(CONST.PATH_NAME, pa)).collect(Collectors.toList()));
		}
		data.add(new BasicNameValuePair(CONST.AJAX_KEY, CONST.AJAX_VAL));
		request.setEntity(new UrlEncodedFormEntity(data, Consts.UTF_8));
		return request;
	}

}
