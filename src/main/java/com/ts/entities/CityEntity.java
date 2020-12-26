package com.ts.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Table(name= "CITY_MASTER")
@Data
@Entity
public class CityEntity{

    @Id
    @GeneratedValue
    @Column(name="CITY_ID")
    private Integer cityId;

    @Column(name="CITY_NAME")
    private String cityName;

    @Column(name="STATE_ID")
    private Integer stateId;

} 
