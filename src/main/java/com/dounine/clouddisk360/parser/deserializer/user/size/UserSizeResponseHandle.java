package com.dounine.clouddisk360.parser.deserializer.user.size;

import org.apache.http.client.ResponseHandler;

import com.dounine.clouddisk360.parser.UserSizeParser;
import com.dounine.clouddisk360.parser.deserializer.BaseResponseHandle;

import java.util.regex.Matcher;

public class UserSizeResponseHandle extends BaseResponseHandle<UserSize, UserSizeParser>
		implements ResponseHandler<UserSize> {

	public UserSizeResponseHandle(final UserSizeParser parse) {
		super(parse);
	}

	public String disassemblyResult(final String result) {
		final Matcher matcher = VAL_PAT.matcher(result);
		if(matcher.find()){
			return matcher.group();
		}
		return result;
	}

}
