package com.dounine.clouddisk360.parser.deserializer.file.list;

import com.alibaba.fastjson.JSON;
import com.dounine.clouddisk360.parser.deserializer.BaseDes;

import java.util.List;

public class FileList extends BaseDes {

	private String loginFlag;
	private Integer page;
	private String listType;
	private String field;
	private String order;
	private String type;
	private String previewSign;
	private Integer fileNum;
	private List<FileListData> data;

	public String getLoginFlag() {
		return loginFlag;
	}

	public void setLoginFlag(String loginFlag) {
		this.loginFlag = loginFlag;
	}

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public String getListType() {
		return listType;
	}

	public void setListType(String listType) {
		this.listType = listType;
	}

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getPreviewSign() {
		return previewSign;
	}

	public void setPreviewSign(String previewSign) {
		this.previewSign = previewSign;
	}

	public Integer getFileNum() {
		return fileNum;
	}

	public void setFileNum(Integer fileNum) {
		this.fileNum = fileNum;
	}

	@Override
	public String toString() {
		return JSON.toJSONString(this, true);
	}

	public List<FileListData> getData() {
		return data;
	}

	public void setData(List<FileListData> data) {
		this.data = data;
	}

}
