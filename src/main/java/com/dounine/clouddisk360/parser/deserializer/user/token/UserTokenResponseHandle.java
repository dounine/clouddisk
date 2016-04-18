package com.dounine.clouddisk360.parser.deserializer.user.token;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.client.ResponseHandler;

import com.dounine.clouddisk360.parser.UserTokenParser;
import com.dounine.clouddisk360.parser.deserializer.BaseResponseHandle;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserTokenResponseHandle extends BaseResponseHandle<UserToken, UserTokenParser>
		implements ResponseHandler<UserToken> {

	public static final Pattern VAL_PAT = Pattern.compile("[{].*[}]");

	public UserTokenResponseHandle(UserTokenParser parse) {
		super(parse);
	}

	public String disassemblyResult(String result) {
		Matcher matcher = VAL_PAT.matcher(result);
		if(matcher.find()){
			return matcher.group();
		}
		return StringUtils.EMPTY;
	}

}
