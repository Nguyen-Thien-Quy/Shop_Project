package com.r2s.demo.constant;

public enum Constant {
	SUCCESS(200, "OK"),
	NOT_FOUND(404, "NOT FOUND"),
	NO_PARAM(6001, "NO PARAM"),
	OBJECT_IS_NULL(1009, "OBJECT IS NULL"),
	LOGIN_FALSE(2023, "LOGIN IS FALSE"),
	NO_CONTENT(2004, "NO CONTENT");
	
	private int code;
	private String message;
	public int getCode() {
		return code;
	}
	public String getMessage() {
		return message;
	}
	private Constant(int code, String message) {
		this.code = code;
		this.message = message;
	}
	
	
}
