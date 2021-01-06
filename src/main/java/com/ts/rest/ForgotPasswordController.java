package com.ts.rest;

import java.io.IOException;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.ts.Service.UserServiceImpl;

@RestController
public class ForgotPasswordController {

	@Autowired
	UserServiceImpl userServiceImpl;
	
	@GetMapping("/forgetPass/{email}")
	public ResponseEntity<String> forgotPassword(@PathVariable("email") String email) throws MessagingException, IOException{
		
		boolean unique = userServiceImpl.isEmailUnique(email);
		 
		if(unique) {
			boolean forgotPass = userServiceImpl.forgotPass(email);
			if (forgotPass) {
				return new ResponseEntity<String>("Email Sent Successfully", HttpStatus.BAD_REQUEST);
			}
		}
		
		return new ResponseEntity<String>("User does not exists with the following email", HttpStatus.BAD_REQUEST);
	}
}
