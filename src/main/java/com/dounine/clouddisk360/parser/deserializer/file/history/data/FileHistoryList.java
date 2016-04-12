package com.dounine.clouddisk360.parser.deserializer.file.history.data;

public class FileHistoryList {

	private String ctime;
	private String fhash;
	private Long fsize;
	private String id;
	private String mtime;
	private String nid;
	private String qid;
	private String scid;
	private Integer status;
	private Integer type;
	private Integer version;

	public String getCtime() {
		return ctime;
	}

	public void setCtime(String ctime) {
		this.ctime = ctime;
	}

	public String getFhash() {
		return fhash;
	}

	public void setFhash(String fhash) {
		this.fhash = fhash;
	}

	public Long getFsize() {
		return fsize;
	}

	public void setFsize(Long fsize) {
		this.fsize = fsize;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public String getQid() {
		return qid;
	}

	public void setQid(String qid) {
		this.qid = qid;
	}

	public String getScid() {
		return scid;
	}

	public void setScid(String scid) {
		this.scid = scid;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

}
