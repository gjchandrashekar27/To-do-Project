package com.jsp.todo_rest_api.entity;

import java.time.LocalDateTime;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import com.jsp.todo_rest_api.dto.TaskRequest;
import com.jsp.todo_rest_api.helper.TaskStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Task {
	
	

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String name;
	
	private String description;
	
	@Enumerated(EnumType.STRING)
	private TaskStatus  status;
	
	@CreationTimestamp
	private LocalDateTime createdTime;
	
	@UpdateTimestamp
	private LocalDateTime updatedTime;
	
	private Long userId;
	
	public Task(TaskRequest request, Long user_id) {
		this.name = request.getName();
		this.description = request.getDescription();
		this.status = TaskStatus.valueOf(request.getStatus());
		this.userId = user_id;
	}

}
