package com.ts.Service;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.ts.entities.UserEntity;



public interface UserService {

	//Register Page 

    public Map<Integer, String> findCountries();

    public Map<Integer, String> findState(Integer countryId);

    public Map<Integer, String> findCity(Integer stateId);

    public boolean isEmailUnique(String email);

    public boolean saveUser(UserEntity user);

    // Login Page

    public boolean loginCheck(String email, String password);

    //Unlock Account Operations

    public boolean isTempValid(String email, String password);

    public boolean unlockAccount(String email, String newpass);

    // Forget Password

    public String forgetPass(String email);
}
