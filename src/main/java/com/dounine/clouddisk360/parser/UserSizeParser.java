package com.dounine.clouddisk360.parser;

import com.dounine.clouddisk360.annotation.Dependency;
import com.dounine.clouddisk360.annotation.Parse;
import com.dounine.clouddisk360.parser.deserializer.login.LoginUserToken;
import com.dounine.clouddisk360.parser.deserializer.user.size.*;
import com.dounine.clouddisk360.util.TimeUtil;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URISyntaxException;

@Parse("云盘空间信息")
@Dependency(depends={AuthTokenParser.class})
public class UserSizeParser extends
		BaseParser<HttpGet, UserSize, UserSizeConst, UserSizeParameter, UserSizeRequestInterceptor, UserSizeResponseHandle,UserSizeParser> {

	public UserSizeParser(){
		super();
	}
	public UserSizeParser(LoginUserToken loginUser) {
		super(loginUser);
	}

	private static final Logger LOGGER = LoggerFactory.getLogger(UserSizeParser.class);

	public void writeDisk(UserSize userSize) {
		super.writeObjToDisk(userSize);
	}

	public UserSize readDisk() {
		return super.readObjForDisk(UserSize.class);
	}

	@Override
	public HttpGet initRequest(UserSizeParameter userSizeParameter) {
		try {
			URIBuilder uri = new URIBuilder(getRequestUri());
			uri.setParameter("t", TimeUtil.getTimeLenth(10));
			HttpGet request = new HttpGet(uri.build());
			return request;
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		return null;
	}

}
