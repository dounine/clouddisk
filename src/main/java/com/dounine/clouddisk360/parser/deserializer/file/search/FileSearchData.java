package com.dounine.clouddisk360.parser.deserializer.file.search;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSON;

public class FileSearchData {

	private List<FileSearchList> node_list = new ArrayList<>(0);
	private Long retnum;
	private Long total;

	public List<FileSearchList> getNode_list() {
		return node_list;
	}

	public void setNode_list(List<FileSearchList> node_list) {
		this.node_list = node_list;
	}

	public Long getRetnum() {
		return retnum;
	}

	public void setRetnum(Long retnum) {
		this.retnum = retnum;
	}

	public Long getTotal() {
		return total;
	}

	public void setTotal(Long total) {
		this.total = total;
	}

	@Override
	public String toString() {
		return JSON.toJSONString(this, true);
	}

}
