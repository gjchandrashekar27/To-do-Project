package com.jsp.todo_rest_api.service;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.RequestEntity;
import org.springframework.stereotype.Service;

import com.jsp.todo_rest_api.dto.UserRequest;
import com.jsp.todo_rest_api.entity.Session;
import com.jsp.todo_rest_api.entity.User;
import com.jsp.todo_rest_api.exception.InvalidException;
import com.jsp.todo_rest_api.exception.UserExistsException;
import com.jsp.todo_rest_api.helper.AES;
import com.jsp.todo_rest_api.helper.SessionStatus;
import com.jsp.todo_rest_api.repository.SessionRepository;
import com.jsp.todo_rest_api.repository.UserRepository;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Service
public class UserServiceImpl  implements UserService{

    private final SessionRepository sessionRepository;
	
	@Autowired
	UserRepository userRepository;

    UserServiceImpl(SessionRepository sessionRepository) {
        this.sessionRepository = sessionRepository;
    }

	@Override
	public Map<String, String> registerUser(UserRequest request) {
		if (userRepository.existsByEmail(request.getEmail()))
			throw new UserExistsException("Email Already Exists");
		if (userRepository.existsByUsername(request.getUsername()))
			throw new UserExistsException("Username Already Exists");
		
		User user = new User(request);
		userRepository.save(user);
		Map<String, String> map = new LinkedHashMap<String, String>();
		map.put("message", "User Registered Successfully");
		return map;
	}

	@Override
	public Map<String, String> login(UserRequest request, HttpSession session) {
		Optional<User> user = userRepository.findByUsername(request.getUsername());
		if(user.isPresent()) {
			if(request.getPassword().equals(AES.decrypt(user.get().getPassword()))) {
				
				Session userSession = new Session();
				userSession.setSessionId(session.getId());
				userSession.setUser_id(user.get().getId());
				userSession.setStatus(SessionStatus.ACTIVE);
				sessionRepository.save(userSession);
				
				Map<String, String> map = new LinkedHashMap<String, String>();
				map.put("message", "User Login Success");
				map.put("sessionId", session.getId());
				return map;
				
			}else
				throw new InvalidException("Invalid Password");
			
		}else
			throw new InvalidException("Invalid Username");
	}

	
	
	}
	



