package com.jsp.todo_rest_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jsp.todo_rest_api.entity.Session;

public interface SessionRepository extends JpaRepository<Session, Long>{

}
