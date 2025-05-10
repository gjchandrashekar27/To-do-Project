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
import com.jsp.todo_rest_api.exception.NotAllowedException;
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
	    if (checkSession(sessionId)) {
	        Session userSession = sessionRepository.findBySessionId(sessionId);
	        System.err.println(userSession.getUserId());

	        List<Task> tasks = taskRepository.findByUserId(userSession.getUserId());

	        if (!tasks.isEmpty()) {
	            Map<String, Object> map = new LinkedHashMap<>();
	            map.put("message", "Task Found Success");
	            map.put("data", tasks);
	            return map; // ✅ YOU MISSED THIS RETURN
	        }

	        throw new ResourceNotFound("No Records Present");
	    }

	    throw new InvalidSessionException();
	}


	@Override
	public Map<String, Object> fetchTaskById(String sessionId, Long id) {
		if (checkSession(sessionId)) {
			Session userSession = sessionRepository.findBySessionId(sessionId);
			Task task = taskRepository.findById(id).orElseThrow(() -> new ResourceNotFound("No Task from Id :" + id));
			System.err.println(task.getUserId()+"   "+userSession.getUserId());
			if(task.getUserId()==userSession.getUserId()) {
				Map<String, Object> map = new LinkedHashMap<String, Object>();
				map.put("message", "Task Found Success");
				map.put("data", task);
				return map;
			}
			throw new NotAllowedException("You can See Task only that You have added");
		}
		throw new InvalidSessionException();
	}

	@Override
	public Map<String, Object> deleteTaskById(String sessionId, Long id) {
		if (checkSession(sessionId)) {
			Session userSession = sessionRepository.findBySessionId(sessionId);
			Task task = taskRepository.findById(id).orElseThrow(() -> new ResourceNotFound("No Task from Id :" + id));

			if (task.getUserId() == userSession.getUserId()) {
				taskRepository.deleteById(id);
				Map<String, Object> map = new LinkedHashMap<String, Object>();
				map.put("message", "Task Deleted Success");
				return map;
			}
			throw new NotAllowedException("You can Delete Task only that You have added");
		}
		throw new InvalidSessionException();
	
	}

	@Override
	public Map<String, Object> updateTask(Task task, String sessionId) {
		if (checkSession(sessionId)) {
			Session userSession = sessionRepository.findBySessionId(sessionId);
			Task task1 = taskRepository.findById(task.getId())
					.orElseThrow(() -> new ResourceNotFound("No Task from Id :" + task.getId()));
			if (task.getUserId() == userSession.getUserId()) {
				taskRepository.save(task);
				Map<String, Object> map = new LinkedHashMap<String, Object>();
				map.put("message", "Task Update Success");
				map.put("data", task);
				return map;	
		}
			throw new NotAllowedException("You can Delete Task only that You have added");	
	}
		throw new InvalidSessionException();
	}
}

