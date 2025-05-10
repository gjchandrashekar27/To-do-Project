package com.jsp.todo_rest_api.entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import com.jsp.todo_rest_api.helper.SessionStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;



@Entity
@Getter
@Setter
public class Session {
	
	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private Long id;
	
	private String sessionId;
	
	private Long userId;
	
	@CreationTimestamp
	private LocalDateTime loggedInTime;
	
	private LocalDateTime loggedOutTime;
	
	@Enumerated(EnumType.STRING)
	private SessionStatus status;

	

}
