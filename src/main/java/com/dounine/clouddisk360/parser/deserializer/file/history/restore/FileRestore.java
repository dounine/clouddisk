package com.dounine.clouddisk360.parser.deserializer.file.history.restore;

import com.alibaba.fastjson.JSON;
import com.dounine.clouddisk360.parser.deserializer.BaseDes;

public class FileRestore extends BaseDes {


	@Override
	public String toString() {
		return JSON.toJSONString(this, true);
	}

}
