package com.ts.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ts.Service.UserServiceImpl;
import com.ts.binding.UserBody;

@RestController
public class UserUnlockController {

	@Autowired
	UserServiceImpl userServiceImpl;
	
	@GetMapping("/unlockUser")
	public ResponseEntity<String> unlockAccount(@RequestBody UserBody userBody){
		
		boolean b = userServiceImpl.isEmailUnique(userBody.getEmail());
		if(b) {
			boolean unlockAccount = userServiceImpl.unlockAccount(userBody.getEmail(), userBody.getNewPass());
			if(unlockAccount) {
				return new ResponseEntity<String>("Account unlocked Succesfully", HttpStatus.OK); 
			}
		}
			return new ResponseEntity<String>("User does not found with the given email id", HttpStatus.BAD_REQUEST);
	}
	
	
	
	
	
	
	
}
