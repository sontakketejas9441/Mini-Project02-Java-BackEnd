package com.ts.repository;

import java.io.Serializable;
import java.util.List;

import com.ts.entities.StateEntity;

import org.springframework.data.jpa.repository.JpaRepository;

public interface StateEntityRepository extends JpaRepository<StateEntity, Serializable>{
	
	public List<StateEntity> findByCountryId(Integer countryId);

}
