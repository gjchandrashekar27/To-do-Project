package com.jsp.todo_rest_api.service;

import java.util.Map;

import com.jsp.todo_rest_api.dto.UserRequest;

import jakarta.servlet.http.HttpSession;



public interface UserService {

	Map<String, String> registerUser( UserRequest request);

	Map<String, String> login(UserRequest request, HttpSession session);

}
