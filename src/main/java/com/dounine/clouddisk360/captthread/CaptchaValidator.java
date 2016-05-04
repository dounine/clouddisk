package com.dounine.clouddisk360.captthread;

import java.time.LocalDateTime;

public class CaptchaValidator {

	private String captchaValue;
	private LocalDateTime addTime;
	private LocalDateTime validTime;
	private String captFilePath;
	private String validMsg;
	private boolean success;

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getValidMsg() {
		return validMsg;
	}

	public void setValidMsg(String validMsg) {
		this.validMsg = validMsg;
	}

	public String getCaptFilePath() {
		return captFilePath;
	}

	public void setCaptFilePath(String captFilePath) {
		this.captFilePath = captFilePath;
	}

	public String getCaptchaValue() {
		return captchaValue;
	}

	public void setCaptchaValue(String captchaValue) {
		this.captchaValue = captchaValue;
	}

	public LocalDateTime getAddTime() {
		return addTime;
	}

	public void setAddTime(LocalDateTime addTime) {
		this.addTime = addTime;
	}

	public LocalDateTime getValidTime() {
		return validTime;
	}

	public void setValidTime(LocalDateTime validTime) {
		this.validTime = validTime;
	}

}
