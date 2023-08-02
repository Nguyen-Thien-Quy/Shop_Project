package com.r2s.demo.DTO;

public class AuthenDTO {
	private String userName;
	private String password;
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public AuthenDTO(String userName, String password) {
		super();
		this.userName = userName;
		this.password = password;
	}
	public AuthenDTO() {
		super();
	}
	
	
}
