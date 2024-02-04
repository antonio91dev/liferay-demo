package com.vass.reniec.pe.service.api;

public class Credentials {

	public Credentials(String user, String password) {
		this.user = user;
		this.password = password;
	}

	public String getPassword() {
		return password;
	}

	public String getUser() {
		return user;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setUser(String user) {
		this.user = user;
	}

	private String password;
	private String user;

}