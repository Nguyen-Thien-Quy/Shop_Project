package com.r2s.demo.DTO;

public class AuthenDTOResponses {
	private String token;
	private String message;
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public AuthenDTOResponses(String token, String message) {
		super();
		this.token = token;
		this.message = message;
	}
	
	
}
