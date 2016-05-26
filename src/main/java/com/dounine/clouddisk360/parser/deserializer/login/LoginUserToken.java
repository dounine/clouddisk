package com.dounine.clouddisk360.parser.deserializer.login;

import java.io.Serializable;

 
public class LoginUserToken implements Serializable{

	private String account;
	private String password;
	private boolean md5 = true;

	public LoginUserToken(){}

	public LoginUserToken(String account, String password) {
		this.account = account;
		this.password = password;
	}

	public LoginUserToken(String account, String password,boolean md5Enc) {
		this.account = account;
		this.password = password;
		this.md5 = md5Enc;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isMd5() {
		return md5;
	}

	public void setMd5(boolean md5) {
		this.md5 = md5;
	}

}
