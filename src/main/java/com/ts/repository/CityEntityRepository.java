package com.ts.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ts.entities.CityEntity;

public interface CityEntityRepository extends JpaRepository<CityEntity, Serializable>{

	public List<CityEntity> findByStateId(Integer stateId);
}
