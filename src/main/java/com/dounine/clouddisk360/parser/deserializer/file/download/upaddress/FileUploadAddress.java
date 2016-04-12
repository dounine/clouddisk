package com.dounine.clouddisk360.parser.deserializer.file.download.upaddress;

import com.alibaba.fastjson.JSON;
import com.dounine.clouddisk360.parser.deserializer.BaseDes;

public class FileUploadAddress extends BaseDes {

	private FileUploadAddressData data;

	public FileUploadAddressData getData() {
		return data;
	}

	public void setData(FileUploadAddressData data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return JSON.toJSONString(this, true);
	}

}
