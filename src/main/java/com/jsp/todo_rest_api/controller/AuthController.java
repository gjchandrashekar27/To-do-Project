package com.jsp.todo_rest_api.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.jsp.todo_rest_api.dto.UserRequest;
import com.jsp.todo_rest_api.service.UserService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
	
	@Autowired
	UserService userService;
	
	//Registration
	@PostMapping("/register")
	public ResponseEntity<Map<String, String>> registerUser(@RequestBody @Valid UserRequest request){
		return ResponseEntity.status(HttpStatus.CREATED).body(userService.registerUser(request));
	}
	
	//Login
	@PostMapping("/login")
	public ResponseEntity<Map<String, String>> login (@RequestBody UserRequest request, HttpSession session){
		return ResponseEntity.status(HttpStatus.OK).body(userService.login(request,session));		
	}
	
	//Logout
	@PostMapping("/logout")
	public ResponseEntity<Map<String, String>> logout(@RequestHeader(required = false) String sessionId, HttpSession session){
		return ResponseEntity.status(HttpStatus.OK).body(userService.logout(sessionId,session));
	}

}
