package com.ts.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.ResponseEntity;

import com.ts.entities.UserEntity;

public interface UserEntityRepository extends JpaRepository<UserEntity, Serializable>{

	ResponseEntity<UserEntity> findByEmail(String email);
	
	ResponseEntity<UserEntity> findByEmailAndPassword(String email, String password);
}
