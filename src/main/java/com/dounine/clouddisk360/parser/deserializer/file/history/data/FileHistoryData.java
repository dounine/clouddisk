package com.dounine.clouddisk360.parser.deserializer.file.history.data;

import java.util.List;

public class FileHistoryData {

	private FileHistoryEvent current;
	private List<FileHistoryList> fhistory_list;
	private String event;
	private String retnum;

	public FileHistoryEvent getCurrent() {
		return current;
	}

	public void setCurrent(FileHistoryEvent current) {
		this.current = current;
	}

	public List<FileHistoryList> getFhistory_list() {
		return fhistory_list;
	}

	public void setFhistory_list(List<FileHistoryList> fhistory_list) {
		this.fhistory_list = fhistory_list;
	}

	public String getEvent() {
		return event;
	}

	public void setEvent(String event) {
		this.event = event;
	}

	public String getRetnum() {
		return retnum;
	}

	public void setRetnum(String retnum) {
		this.retnum = retnum;
	}

}
