package com.ts.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.ResponseEntity;

import com.ts.entities.UserEntity;

public interface UserEntityRepository extends JpaRepository<UserEntity, Serializable>{

	public UserEntity findByEmail(String email);
	
	public UserEntity findByEmailAndPassword(String email, String password);
}
