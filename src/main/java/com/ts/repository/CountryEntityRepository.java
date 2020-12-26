package com.ts.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ts.entities.CountryEntity;

public interface CountryEntityRepository extends JpaRepository<CountryEntity, Serializable>{

}
