package com.softserve.edu.opencart.data;

public class UserUkrNet implements IUserUkrNet {

	private String eMail;
	private String password;

	public UserUkrNet(String eMail, String password) {
		this.eMail = eMail;
		this.password = password;
	}

	public void seteMail(String eMail) {
		this.eMail = eMail;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String geteMail() {
		return eMail;
	}

	public String getPassword() {
		return password;
	}

}
