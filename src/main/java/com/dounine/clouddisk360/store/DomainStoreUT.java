package com.dounine.clouddisk360.store;

import org.apache.commons.lang3.StringUtils;

import com.dounine.clouddisk360.exception.CloudDiskException;

public class DomainStoreUT {

	private static final DomainStoreUT domainStoreUT = new DomainStoreUT();
	private String domain;

	private DomainStoreUT() {}
	
	public static DomainStoreUT getInstance() {
		return domainStoreUT;
	}
	
	public static String parserDomain(String uri){
		uri = uri.substring(uri.indexOf("//")+2,uri.indexOf('.'));
		return uri;
	}
	
	public void initDomain(String domain) {
		if(StringUtils.isBlank(domain)){
			throw new CloudDiskException("domain子域名不能为空");
		}
		domain = parserDomain(domain);//解释
		domainStoreUT.setDomain(domain);
	}

	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}

}
