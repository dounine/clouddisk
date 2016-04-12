package com.dounine.clouddisk360.parser.deserializer.file.history.data;

public class FileHistoryEvent {

	private String fname;
	private String fpath;
	private Long fsize;
	private String mtime;
	private String nid;
	private String pic;
	private String qid;

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public String getFpath() {
		return fpath;
	}

	public void setFpath(String fpath) {
		this.fpath = fpath;
	}

	public Long getFsize() {
		return fsize;
	}

	public void setFsize(Long fsize) {
		this.fsize = fsize;
	}

	public String getMtime() {
		return mtime;
	}

	public void setMtime(String mtime) {
		this.mtime = mtime;
	}

	public String getNid() {
		return nid;
	}

	public void setNid(String nid) {
		this.nid = nid;
	}

	public String getPic() {
		return pic;
	}

	public void setPic(String pic) {
		this.pic = pic;
	}

	public String getQid() {
		return qid;
	}

	public void setQid(String qid) {
		this.qid = qid;
	}

}
