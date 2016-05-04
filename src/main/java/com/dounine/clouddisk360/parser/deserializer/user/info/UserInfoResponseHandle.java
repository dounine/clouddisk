package com.dounine.clouddisk360.parser.deserializer.user.info;

import com.dounine.clouddisk360.parser.UserInfoParser;
import com.dounine.clouddisk360.parser.deserializer.BaseResponseHandle;
import org.apache.http.client.ResponseHandler;

public class UserInfoResponseHandle extends BaseResponseHandle<UserInfo, UserInfoParser>
		implements ResponseHandler<UserInfo> {

	public UserInfoResponseHandle(final UserInfoParser parse) {
		super(parse);
	}
}
