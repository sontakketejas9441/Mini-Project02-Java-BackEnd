package com.ts.rest;

import java.security.SecureRandom;
import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ts.Service.UserServiceImpl;
import com.ts.entities.UserEntity;

@RestController

public class UserRegistrationController {

	private static final String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	
	@Autowired
	UserServiceImpl userServiceImpl;
	
	@GetMapping("/countries")
	public Map<Integer, String> getCountries(){
		Map<Integer, String> countries = userServiceImpl.findCountries();
		return countries;
	}

	@GetMapping("/states/{countryId}")
	public Map<Integer, String> getStates(@PathVariable ("countryId") Integer countryId){
		Map<Integer, String> state = userServiceImpl.findState(countryId);
		return state;
	}

	@GetMapping("/cities/{stateId}")
	public Map<Integer, String> getCities(@PathVariable("stateId") Integer stateID){
		Map<Integer, String> city = userServiceImpl.findCity(stateID);
		return city;
	}

	@GetMapping("/emailCheck/{email}")
	public boolean isEmailUnique(@PathVariable("email") String email){

		boolean unique = userServiceImpl.isEmailUnique(email);
		if(unique){
			return true;
		}
		return false;
	}

	@GetMapping("/register")
	public ResponseEntity<String> registerUser(@RequestBody UserEntity userEntity){
		userEntity.setPassword(generateRandom(5));
		userEntity.setIsActive("INACTIVE");
		boolean savedUser = userServiceImpl.saveUser(userEntity);
		
		if (savedUser) {
			return new ResponseEntity<String> ("User Details saved Successfully", HttpStatus.OK);
		}
		return new ResponseEntity<String> ("Please Try again..!!", HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	
    public static String generateRandom(int length) {
        if (length <= 0) {
            throw new IllegalArgumentException("String length must be a positive integer");
        }

        Random random = new SecureRandom();
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            sb.append(characters.charAt(random.nextInt(characters.length())));
        }

        return sb.toString();
    }
    

}
