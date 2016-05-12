package com.dounine.clouddisk360.parser.deserializer.file.download.dladdress;

import com.alibaba.fastjson.JSON;

public class FileDownloadAddressData {

	private String download_url;

	public String getDownloadUrl() {
		return download_url;
	}

	public void setDownloadUrl(String download_url) {
		this.download_url = download_url;
	}

	@Override
	public String toString() {
		return JSON.toJSONString(this, true);
	}

}
