package com.jsp.todo_rest_api.service;

import java.util.Map;

import com.jsp.todo_rest_api.dto.UserRequest;



public interface UserService {

	Map<String, String> registerUser( UserRequest request);

}
