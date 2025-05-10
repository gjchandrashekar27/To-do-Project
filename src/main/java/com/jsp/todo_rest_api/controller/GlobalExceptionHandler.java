package com.jsp.todo_rest_api.controller;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.jsp.todo_rest_api.exception.InvalidException;
import com.jsp.todo_rest_api.exception.InvalidSessionException;
import com.jsp.todo_rest_api.exception.NotAllowedException;
import com.jsp.todo_rest_api.exception.ResourceNotFound;
import com.jsp.todo_rest_api.exception.UserExistsException;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String, String>> handle(MethodArgumentNotValidException exception) {
		Map<String, String> map = new LinkedHashMap<String, String>();
		map.put("message", exception.getFieldError().getDefaultMessage());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(map);
	}
	
	@ExceptionHandler(UserExistsException.class)
	public ResponseEntity<Map<String, String>> handle(UserExistsException exception) {
		Map<String, String> map = new LinkedHashMap<String, String>();
		map.put("message", exception.getMessage());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(map);
	}
	
	@ExceptionHandler(InvalidException.class)
	public ResponseEntity<Map<String, String>> handle(InvalidException exception) {
		Map<String, String> map = new LinkedHashMap<String, String>();
		map.put("message", exception.getMessage());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(map);
	}
	
	@ExceptionHandler(InvalidSessionException.class)
	public ResponseEntity<Map<String, String>> handle(InvalidSessionException exception) {
		Map<String, String> map = new LinkedHashMap<String, String>();
		map.put("message", "Invalid Session, Login Again");
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(map);
	}
	
	@ExceptionHandler(ResourceNotFound.class)
	public ResponseEntity<Map<String, String>> handle(ResourceNotFound exception) {
		Map<String, String> map = new LinkedHashMap<String, String>();
		map.put("message", exception.getMessage());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(map);
	}
	
	@ExceptionHandler(NotAllowedException.class)
	public ResponseEntity<Map<String, String>> handle(NotAllowedException exception) {
		Map<String, String> map = new LinkedHashMap<String, String>();
		map.put("message", exception.getMessage());
		return ResponseEntity.status(HttpStatus.FORBIDDEN).body(map);
	}

}
