package com.jsp.todo_rest_api.exception;

public class UserExistsException extends RuntimeException {
	
	public UserExistsException(String message) {
		super(message);
	}

}
