package com.dounine.clouddisk360.parser.deserializer.login;

import com.dounine.clouddisk360.util.MD5Util;

import java.io.Serializable;

public class LoginUserToken implements Serializable{

	private String account;
	private String password;

	public LoginUserToken(String account, String password) {
		this.account = account;
		this.password = password;
	}

	public LoginUserToken(String account, String password,boolean md5Enc) {
		this.account = account;
		this.password = password;
        if(!md5Enc){
            this.password = MD5Util.MD5(password);
        }
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

}
