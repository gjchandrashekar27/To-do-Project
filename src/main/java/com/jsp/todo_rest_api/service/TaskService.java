package com.jsp.todo_rest_api.service;

import java.util.Map;

import com.jsp.todo_rest_api.dto.TaskRequest;

import jakarta.validation.Valid;

public interface TaskService {

	Map<String, Object> addTask(@Valid TaskRequest request, String sessionId);

	Map<String, Object> fetchAllTasks(String sessionId);

}
