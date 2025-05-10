package com.jsp.todo_rest_api.service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jsp.todo_rest_api.dto.TaskRequest;
import com.jsp.todo_rest_api.entity.Session;
import com.jsp.todo_rest_api.entity.Task;
import com.jsp.todo_rest_api.exception.InvalidSessionException;
import com.jsp.todo_rest_api.exception.ResourceNotFound;
import com.jsp.todo_rest_api.helper.SessionStatus;
import com.jsp.todo_rest_api.repository.SessionRepository;
import com.jsp.todo_rest_api.repository.TaskRepository;

import jakarta.validation.Valid;

@Service
public class TaskServiceImpl implements TaskService{
	
	@Autowired
	TaskRepository taskRepository;
	
	@Autowired
	SessionRepository sessionRepository;

	@Override
	public Map<String, Object> addTask(@Valid TaskRequest request, String sessionId) {
		if(checkSession(sessionId)) {
			Session userSession = sessionRepository.findBySessionId(sessionId);
			Task task = new Task(request, userSession.getUserId());
			taskRepository.save(task);
			Map<String, Object> map = new LinkedHashMap<String, Object>();
			map.put("message", "Task Added Success");
			map.put("data", task);
			return map;
		}
		throw new InvalidSessionException();
		
	}

	private boolean checkSession(String sessionId) {
		if(sessionId != null) {
			Session userSession = sessionRepository.findBySessionId(sessionId);
			if(userSession != null)
				if(userSession.getStatus() == SessionStatus.ACTIVE)
					return true;
		}
		
		return false;
	}

	@Override
	public Map<String, Object> fetchAllTasks(String sessionId) {
		if(checkSession(sessionId)) {
			Session userSession = sessionRepository.findBySessionId(sessionId);
			System.err.println(userSession.getUserId());
			List<Task>tasks = taskRepository.findByUserId(userSession.getUserId());
			if(!tasks.isEmpty()) {
				Map<String, Object> map = new LinkedHashMap<String,Object>();
				map.put("message", "Task Found Success");
				map.put("data", tasks);
			}
			throw new ResourceNotFound("No Records Present");
		}
		
		throw new InvalidSessionException();
	}

}
