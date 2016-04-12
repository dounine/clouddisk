package com.dounine.clouddisk360.parser.deserializer.file.move;

import com.alibaba.fastjson.JSON;
import com.dounine.clouddisk360.parser.deserializer.BaseDes;

public class FileMove extends BaseDes {

	@Override
	public String toString() {
		return JSON.toJSONString(this, true);
	}

}
