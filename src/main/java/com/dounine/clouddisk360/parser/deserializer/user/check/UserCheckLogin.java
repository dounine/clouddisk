package com.dounine.clouddisk360.parser.deserializer.user.check;

import com.alibaba.fastjson.JSON;
import com.dounine.clouddisk360.parser.deserializer.BaseDes;

public class UserCheckLogin extends BaseDes {

	@Override
	public String toString() {
		return JSON.toJSONString(this);
	}
}
