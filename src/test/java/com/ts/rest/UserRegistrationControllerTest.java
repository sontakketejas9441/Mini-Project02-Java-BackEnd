package com.ts.rest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.sql.Date;
import java.util.HashMap;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ts.Service.UserServiceImpl;
import com.ts.binding.UserSave;
import com.ts.entities.UserEntity;

@WebMvcTest(value = UserRegistrationController.class)
public class UserRegistrationControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private UserServiceImpl userServiceImpl;
	
	@Test
	public void test_countries() throws Exception {

		//Creation of CountryHashMap
		HashMap<Integer, String> countryHashMap = new HashMap<>();
		countryHashMap.put(101, "India");
		countryHashMap.put(102, "USA");
		
		//when...then condition
		when(userServiceImpl.findCountries()).thenReturn(countryHashMap);
		
		 //Creation of requestBuilder object with GET/POST method   
         MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/countries");

        //Perform the request and check the response  
        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();

        int status = mvcResult.getResponse().getStatus();

        //Comparing actual and expected value  
        assertEquals(200, status);
		
		
	}
	
	@Test
	public void test_states() throws Exception {

		//Creation of CountryHashMap
		HashMap<Integer, String> stateHashMap = new HashMap<>();
		stateHashMap.put(1, "Karnataka");
		stateHashMap.put(2, "UP");
		
		Integer countryId = 1;
		
		//when...then condition
		when(userServiceImpl.findState(countryId)).thenReturn(stateHashMap);
		
		 //Creation of requestBuilder object with GET/POST method   
         MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/states/1");

        //Perform the request and check the response  
        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();

        int status = mvcResult.getResponse().getStatus();

        //Comparing actual and expected value  
        assertEquals(200, status);
		
		
	}


	@Test
	public void test_cities() throws Exception {

		//Creation of CountryHashMap
		HashMap<Integer, String> citiesHashMap = new HashMap<>();
		citiesHashMap.put(1, "City1");
		citiesHashMap.put(2, "City2");
		
		
		//when...then condition
		when(userServiceImpl.findCity(1)).thenReturn(citiesHashMap);
		
		 //Creation of requestBuilder object with GET/POST method   
         MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/cities/1");

        //Perform the request and check the response  
        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();

        int status = mvcResult.getResponse().getStatus();

        //Comparing actual and expected value  
        assertEquals(200, status);
		
		
	}

	//UseCase: If service method returns the true value
	@Test
	public void test_isEmailValid_01() throws Exception {
		
		
		//when...then condition
		when(userServiceImpl.isEmailUnique("abc@gmail.com")).thenReturn(true);
		
		 //Creation of requestBuilder object with GET/POST method   
         MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/emailCheck/abc@gmail.com");

        //Perform the request and check the response  
        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();

        //int status = mvcResult.getResponse().getStatus();
        
        String asString = mvcResult.getResponse().getContentAsString();
        
        //Comparing actual and expected value  
        assertEquals("UNIQUE", asString);
		
	}
	
	//UseCase: If service method returns the false value
	@Test
	public void test_isEmailValid_02() throws Exception {
		
		
		//when...then condition
		when(userServiceImpl.isEmailUnique("abc@gmail.com")).thenReturn(false);
		
		 //Creation of requestBuilder object with GET/POST method   
         MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/emailCheck/abc@gmail.com");

        //Perform the request and check the response  
        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();

        //int status = mvcResult.getResponse().getStatus();
        
        String asString = mvcResult.getResponse().getContentAsString();
        
        //Comparing actual and expected value  
        assertEquals("NOT UNIQUE", asString);
		
		
	}
	
	@Test
	public void test_registerUser_01() throws Exception {
		 
		
		//UserEntity user = new UserEntity(101, "ABC","VBB","abc@gmail.com",(long) 1235677, today, "male",1,1,1,"wqqwqw","YES");
		UserEntity user = new UserEntity();
		user.setFirstName("");
		user.setLastName("ss");
		user.setEmail("s");
		user.setPhone((long) 123456789);
		user.setDateOfBirth(new Date(0));
		user.setGender("Male");
		user.setCountry(1);
		user.setState(1);
		user.setCity(1);
	
		//when...then condition
		when(userServiceImpl.saveUser(user)).thenReturn(true);
		
		//ObjectMapper for converting the Java object to Json object
		ObjectMapper mapper =new ObjectMapper();
		String userValueAsString = mapper.writeValueAsString(user);
		
		 //Creation of requestBuilder object with GET/POST method   
         MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/register")
        		 																										.contentType(MediaType.APPLICATION_JSON)
                 																										.content(userValueAsString);

        //Perform the request and check the response  
        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();

        int status = mvcResult.getResponse().getStatus();
        
        //String asString = mvcResult.getResponse().getContentAsString();

        //Comparing actual and expected value  
        assertEquals(400, status);
		
		
	}
	

	
}
