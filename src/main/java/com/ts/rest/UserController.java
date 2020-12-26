package com.ts.rest;

import java.security.SecureRandom;
import java.util.Random;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ts.Service.UserServiceImpl;
import com.ts.entities.UserEntity;

@RestController
public class UserController {

	private static final String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	
	@Autowired
	UserServiceImpl userServiceImpl;
	
	@GetMapping("/register")
	public ResponseEntity<String> registerUser(@RequestBody UserEntity userEntity){
		userEntity.setPassword(generateRandom(5));
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
    
    @GetMapping("/login/{email}/{password}")
    public ResponseEntity<String> userLoginCheck(@PathVariable ("email") String email, @PathVariable ("email") String password){
    	
    	boolean loginChecked = userServiceImpl.loginCheck(email, password);
    	
    	if(loginChecked) {
    		return new ResponseEntity<String> ("Please Activate the account", HttpStatus.OK);
    	}
    	return new ResponseEntity<String> ("Please Try again..!!", HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
