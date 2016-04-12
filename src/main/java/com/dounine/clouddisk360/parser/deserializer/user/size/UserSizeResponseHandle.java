package com.dounine.clouddisk360.parser.deserializer.user.size;

import org.apache.http.client.ResponseHandler;

import com.dounine.clouddisk360.parser.UserSizeParser;
import com.dounine.clouddisk360.parser.deserializer.BaseResponseHandle;

public class UserSizeResponseHandle extends BaseResponseHandle<UserSize, UserSizeParser>
		implements ResponseHandler<UserSize> {

	public UserSizeResponseHandle(UserSizeParser parse) {
		super(parse);
	}

	public String disassemblyResult(String result) {
		return result.substring(result.indexOf("{"), result.lastIndexOf("}") + 1);
	}

}
