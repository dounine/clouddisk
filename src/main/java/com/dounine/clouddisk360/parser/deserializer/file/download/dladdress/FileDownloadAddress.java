package com.dounine.clouddisk360.parser.deserializer.file.download.dladdress;

import com.alibaba.fastjson.JSON;
import com.dounine.clouddisk360.parser.deserializer.BaseDes;

public class FileDownloadAddress extends BaseDes {

	private FileDownloadAddressData data;

	public FileDownloadAddressData getData() {
		return data;
	}

	public void setData(FileDownloadAddressData data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return JSON.toJSONString(this, true);
	}

}
