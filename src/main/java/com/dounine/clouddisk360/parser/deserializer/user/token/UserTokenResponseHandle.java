package com.dounine.clouddisk360.parser.deserializer.user.token;

import org.apache.http.client.ResponseHandler;

import com.dounine.clouddisk360.parser.UserTokenParser;
import com.dounine.clouddisk360.parser.deserializer.BaseResponseHandle;

public class UserTokenResponseHandle extends BaseResponseHandle<UserToken, UserTokenParser>
		implements ResponseHandler<UserToken> {

	public UserTokenResponseHandle(UserTokenParser parse) {
		super(parse);
	}

	public String disassemblyResult(String result) {
		return result.substring(result.indexOf("{"), result.lastIndexOf("}") + 1);
	}

}
