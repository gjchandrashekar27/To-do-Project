package com.jsp.todo_rest_api.exception;

public class ResourceNotFound extends RuntimeException {
	
	public ResourceNotFound(String message) {
		super(message);
	}

}
