package com.dounine.clouddisk360.parser.deserializer.file.create;

import com.alibaba.fastjson.JSON;
import com.dounine.clouddisk360.parser.deserializer.BaseDes;

public class FileCreate extends BaseDes {

	@Override
	public String toString() {
		return JSON.toJSONString(this, true);
	}

}
