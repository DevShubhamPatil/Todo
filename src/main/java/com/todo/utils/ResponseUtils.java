package com.todo.utils;

import java.util.HashMap;
import java.util.Map;
import org.springframework.http.ResponseEntity;

public class ResponseUtils {

	public static ResponseEntity<?> success(Object data) {
		
		Map<String, Object> body  = new HashMap<String, Object>();
		body.put("Status", "SUCCESS");
		body.put("data",data);
		return ResponseEntity.ok(body);
	}
	
	public static ResponseEntity<?> error(Object data){
		Map<String, Object> body = new HashMap<String, Object>();
		body.put("status", "ERROR");
		body.put("data", data);
		return ResponseEntity.ok(body);
	}
}
