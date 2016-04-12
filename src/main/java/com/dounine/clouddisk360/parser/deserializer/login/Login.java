package com.dounine.clouddisk360.parser.deserializer.login;

import com.alibaba.fastjson.JSON;
import com.dounine.clouddisk360.parser.deserializer.BaseDes;

public class Login extends BaseDes {

	private String qid;
	private String userName;
	private String nickName;
	private String loginEmail;
	private String mobile;
	private String imageId;
	private String type;
	private String src;
	private String loginTime;
	private String modifyTime;
	private String refreshCount;
	private String lm;
	private String unSafeLogin;
	private String loginField;
	private String keepAlive;
	private String ver;
	private String crumb;
	private String imageUrl;

	public String getQid() {
		return qid;
	}

	public void setQid(String qid) {
		this.qid = qid;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getLoginEmail() {
		return loginEmail;
	}

	public void setLoginEmail(String loginEmail) {
		this.loginEmail = loginEmail;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getImageId() {
		return imageId;
	}

	public void setImageId(String imageId) {
		this.imageId = imageId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getSrc() {
		return src;
	}

	public void setSrc(String src) {
		this.src = src;
	}

	public String getLoginTime() {
		return loginTime;
	}

	public void setLoginTime(String loginTime) {
		this.loginTime = loginTime;
	}

	public String getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(String modifyTime) {
		this.modifyTime = modifyTime;
	}

	public String getRefreshCount() {
		return refreshCount;
	}

	public void setRefreshCount(String refreshCount) {
		this.refreshCount = refreshCount;
	}

	public String getLm() {
		return lm;
	}

	public void setLm(String lm) {
		this.lm = lm;
	}

	public String getUnSafeLogin() {
		return unSafeLogin;
	}

	public void setUnSafeLogin(String unSafeLogin) {
		this.unSafeLogin = unSafeLogin;
	}

	public String getLoginField() {
		return loginField;
	}

	public void setLoginField(String loginField) {
		this.loginField = loginField;
	}

	public String getKeepAlive() {
		return keepAlive;
	}

	public void setKeepAlive(String keepAlive) {
		this.keepAlive = keepAlive;
	}

	public String getVer() {
		return ver;
	}

	public void setVer(String ver) {
		this.ver = ver;
	}

	public String getCrumb() {
		return crumb;
	}

	public void setCrumb(String crumb) {
		this.crumb = crumb;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	@Override
	public String toString() {
		return JSON.toJSONString(this, true);
	}
}
