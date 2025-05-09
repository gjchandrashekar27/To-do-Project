package com.jsp.todo_rest_api;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jsp.todo_rest_api.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{

	boolean existsByEmail(String email);

	boolean existsByUsername(String username);

	

	

	

}
