package com.ts.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Table(name = "COUNTRY_MASTER")
@Data
@Entity
public class CountryEntity{

    @Id
    @GeneratedValue
    @Column(name="COUNTRY_ID")
    private Integer countryId;

    @Column(name="COUNTRY_NAME")
    private String countryName;

}        
