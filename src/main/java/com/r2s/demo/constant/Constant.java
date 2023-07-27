package com.r2s.demo.constant;

public enum Constant {
	SUCCESS(200, "OK"),
	NOT_FOUND(404, "NOT FOUND"),
	NO_PARAM(6001, "NO PARAM"),
	OBJECT_IS_NULL(1009, "OBJECT IS NULL"),
	NO_CONTENT(2004, "NO CONTENT");
	
	private int code;
	private String massage;
	public int getCode() {
		return code;
	}
	public String getMassage() {
		return massage;
	}
	private Constant(int code, String massage) {
		this.code = code;
		this.massage = massage;
	}
	
	
}
