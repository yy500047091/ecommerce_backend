package com.app.dto;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;

public class Response {
	
	public static ResponseEntity<?> success(Object data) {
		Map<String, Object> map = new HashMap<>();
		map.put("status", "success");
		if(data != null)
			map.put("data", data);
		return ResponseEntity.ok(map);
	}
}
