package com.ts.entities;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.ToString;

@Table(name = "USER_MASTER")
@Data
@Entity
@ToString
public class UserEntity {

	@Id
    @GeneratedValue
    @Column(name="USER_ID")
    private Integer userId;

    @Column(name="FIRST_NAME")
    private String firstName;

    @Column(name="LAST_NAME")
    private String lastName;

    @Column(name="EMAIL")
    private String email;

    @Column(name="PHONE")
    private Long phone;

    @Column(name="DOB")
    private Date dateOfBirth;

    @Column(name="GENDER")
    private String gender;

    @Column(name="COUNTRY")
    private Integer country;

    @Column(name="STATE")
    private Integer state;

    @Column(name="CITY")
    private Integer city;

    @Column(name="PASSWORD")
    private String password;

    @Column(name="ACTIVE_SWITCH")
    private String isActive;
}
