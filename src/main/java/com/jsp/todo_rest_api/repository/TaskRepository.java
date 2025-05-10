package com.jsp.todo_rest_api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jsp.todo_rest_api.entity.Task;

public interface TaskRepository extends JpaRepository<Task, Long>{

	List<Task> findByUserId(Long user_id);

}
