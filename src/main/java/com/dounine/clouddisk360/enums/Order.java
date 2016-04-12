package com.dounine.clouddisk360.enums;

public enum Order {

	ASC("asc"),
	DESC("desc");
	
	private String value;
	
	private Order(String value){
		this.value = value;
	}

	public String getValue() {
		return value;
	}
}
