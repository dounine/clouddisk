package com.dounine.clouddisk360.parser.deserializer.user.info;

import com.alibaba.fastjson.JSON;
import com.dounine.clouddisk360.parser.deserializer.BaseDes;

public class UserInfo extends BaseDes {

	private Long timestamp;
	private String qid;
	private String username;
	private String nickname;
	private String login_email;
	private String userName;
	private String crumb;
	private String img_url;
	private String type;

	@Override
	public String toString() {
		return JSON.toJSONString(this);
	}

	public Long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Long timestamp) {
		this.timestamp = timestamp;
	}

	public String getQid() {
		return qid;
	}

	public void setQid(String qid) {
		this.qid = qid;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getLogin_email() {
		return login_email;
	}

	public void setLogin_email(String loginEmail) {
		this.login_email = loginEmail;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getCrumb() {
		return crumb;
	}

	public void setCrumb(String crumb) {
		this.crumb = crumb;
	}

	public String getImg_url() {
		return img_url;
	}

	public void setImg_url(String imgUrl) {
		this.img_url = imgUrl;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}
