package com.dounine.clouddisk360.parser.deserializer.user.size;

import com.alibaba.fastjson.JSON;
import com.dounine.clouddisk360.parser.deserializer.BaseDes;

public class UserSize extends BaseDes {

	private Long total_size;
	private Long used_size;

	public Long getTotal_size() {
		return total_size;
	}

	public void setTotal_size(Long totalSize) {
		this.total_size = totalSize;
	}

	public Long getUsed_size() {
		return used_size;
	}

	public void setUsed_size(Long usedSize) {
		this.used_size = usedSize;
	}

	@Override
	public String toString() {
		return JSON.toJSONString(this, true);
	}

}
