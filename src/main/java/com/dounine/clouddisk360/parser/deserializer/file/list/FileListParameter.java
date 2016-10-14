package com.dounine.clouddisk360.parser.deserializer.file.list;

import com.dounine.clouddisk360.enums.Order;
import com.dounine.clouddisk360.parser.deserializer.BaseParameter;

public class FileListParameter extends BaseParameter{

	public static final String ROOT_PATH = "/";

	private Integer type = 2;
	private Order order = Order.DESC;
	private String file_name;
	private String path = ROOT_PATH;
	private Integer page = 0;
	private Integer page_size = 300;

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public String getFile_name() {
		return file_name;
	}

	public void setFile_name(String fileName) {
		this.file_name = fileName;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public Integer getPage_size() {
		return page_size;
	}

	public void setPage_size(Integer pageSize) {
		this.page_size = pageSize;
	}

}
