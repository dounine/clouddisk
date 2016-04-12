package com.dounine.clouddisk360.parser.deserializer;

public class BaseDes {

	protected Integer errno = new Integer(0);
	protected String errmsg;
	protected String cddmsg;

	public Integer getErrno() {
		return errno;
	}

	public void setErrno(Integer errno) {
		this.errno = errno;
	}

	public String getErrmsg() {
		return errmsg;
	}

	public void setErrmsg(String errmsg) {
		this.errmsg = errmsg;
	}

	public String getCddmsg() {
		return cddmsg;
	}

	public void setCddmsg(String cddmsg) {
		this.cddmsg = cddmsg;
	}
}
