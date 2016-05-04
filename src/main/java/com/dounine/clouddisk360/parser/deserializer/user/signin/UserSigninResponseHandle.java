package com.dounine.clouddisk360.parser.deserializer.user.signin;

import org.apache.http.client.ResponseHandler;

import com.dounine.clouddisk360.parser.UserSigninParser;
import com.dounine.clouddisk360.parser.deserializer.BaseResponseHandle;

public class UserSigninResponseHandle extends BaseResponseHandle<UserSignin, UserSigninParser>
		implements ResponseHandler<UserSignin> {

	public UserSigninResponseHandle(final UserSigninParser parse) {
		super(parse);
	}

}
