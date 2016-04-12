package com.dounine.clouddisk360.parser.deserializer.file.info;

import com.alibaba.fastjson.JSON;
import com.dounine.clouddisk360.parser.deserializer.BaseDes;

public class FileInfo extends BaseDes {

	private String oriName;
	private String path;
	private String nid;
	private String date;
	private Integer isDir;
	private String isFav;
	private String mtime;
	private String fmtime;
	private Long oriSize;
	private String size;
	private String scid;
	private String preview;
	private String hasThumb;
	private String thumb;
	private String fhash;
	private String pic;
	private String fileType;

	public String getOriName() {
		return oriName;
	}

	public void setOriName(String oriName) {
		this.oriName = oriName;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getNid() {
		return nid;
	}

	public void setNid(String nid) {
		this.nid = nid;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public Integer getIsDir() {
		return isDir;
	}

	public void setIsDir(Integer isDir) {
		this.isDir = isDir;
	}

	public String getIsFav() {
		return isFav;
	}

	public void setIsFav(String isFav) {
		this.isFav = isFav;
	}

	public String getMtime() {
		return mtime;
	}

	public void setMtime(String mtime) {
		this.mtime = mtime;
	}

	public String getFmtime() {
		return fmtime;
	}

	public void setFmtime(String fmtime) {
		this.fmtime = fmtime;
	}

	public Long getOriSize() {
		return oriSize;
	}

	public void setOriSize(Long oriSize) {
		this.oriSize = oriSize;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public String getScid() {
		return scid;
	}

	public void setScid(String scid) {
		this.scid = scid;
	}

	public String getPreview() {
		return preview;
	}

	public void setPreview(String preview) {
		this.preview = preview;
	}

	public String getHasThumb() {
		return hasThumb;
	}

	public void setHasThumb(String hasThumb) {
		this.hasThumb = hasThumb;
	}

	public String getThumb() {
		return thumb;
	}

	public void setThumb(String thumb) {
		this.thumb = thumb;
	}

	public String getFhash() {
		return fhash;
	}

	public void setFhash(String fhash) {
		this.fhash = fhash;
	}

	public String getPic() {
		return pic;
	}

	public void setPic(String pic) {
		this.pic = pic;
	}

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	@Override
	public String toString() {
		return JSON.toJSONString(this, true);
	}

}
