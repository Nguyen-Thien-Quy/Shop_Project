package com.r2s.demo.constant;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class BaseRestController {
	public ResponseEntity<Map<String, Object>> success(Object data){
		Map<String, Object> response = new HashMap<>();
		response.put("code", 200);
		response.put("massage", "OK");
		response.put("data", data);
		return new ResponseEntity<Map<String,Object>>(response, HttpStatus.OK);
	}
	
	public ResponseEntity<Map<String, Object>> eror(int code, String massage){
		Map<String, Object> response = new HashMap<>();
		response.put("code", code);
		response.put("massage", massage);
		response.put("data", null);
		return new ResponseEntity<Map<String,Object>>(response, HttpStatus.OK);
	}
}
