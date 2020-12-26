package com.ts.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ts.entities.CityEntity;
import com.ts.entities.CountryEntity;
import com.ts.entities.StateEntity;
import com.ts.entities.UserEntity;
import com.ts.repository.CityEntityRepository;
import com.ts.repository.CountryEntityRepository;
import com.ts.repository.StateEntityRepository;
import com.ts.repository.UserEntityRepository;

@Service
public class UserServiceImpl  implements UserService{
	
	@Autowired
	UserEntityRepository userRepo;
	
	@Autowired
	CountryEntityRepository countryRepo;
	
	@Autowired
	StateEntityRepository stateRepo;
	
	@Autowired
	CityEntityRepository cityRepo;
	
	@Override
	public Map<Integer, String> findCountries() {
		// TODO Auto-generated method stub
		List<CountryEntity> countries = countryRepo.findAll();
		
		HashMap<Integer, String> countryHashMap = new HashMap<>();
		
		countries.forEach(country-> {
			countryHashMap.put(country.getCountryId(), country.getCountryName());
		});
		return countryHashMap;
	}

	@Override
	public Map<Integer, String> findState(Integer countryId) {
		// TODO Auto-generated method stub
		List<StateEntity> states = stateRepo.findByCountryId(countryId);
		
		HashMap< Integer, String> statesHashMap = new HashMap<>();
		
		states.forEach(state->{
			statesHashMap.put(state.getStateId(), state.getStateName());
		});
		return statesHashMap;
	}

	@Override
	public Map<Integer, String> findCity(Integer stateId) {
		// TODO Auto-generated method stub
		List<CityEntity> cities = cityRepo.findByStateId(stateId);
		
		HashMap<Integer, String> cityHashMap = new HashMap<>();
		
		cities.forEach(city->{
			cityHashMap.put(city.getCityId(), city.getCityName());
		});
		return cityHashMap;
	}

	@Override
	public boolean isEmailUnique(String email) {
		// TODO Auto-generated method stub
		
		//List<UserEntity> findByEmail = userRepo.findByEmail(email);
		ResponseEntity<UserEntity> userEntity = userRepo.findByEmail(email);
		
		if(userEntity.getBody() != null) {
			return true;
		}
		return false;
	}

	@Override
	public boolean saveUser(UserEntity user) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean loginCheck(String email, String password) {
		// TODO Auto-generated method stub
		ResponseEntity<UserEntity> user = userRepo.findByEmailAndPassword(email, password);
		
		if (user.getBody() != null){
			return true;
		}
		return false;
	}

	@Override
	public boolean isTempValid(String email, String password) {
		ResponseEntity<UserEntity> user = userRepo.findByEmailAndPassword(email, password);
		
		if (user.getBody() != null){
			return true;
		}
		return false;
	}

	@Override
	public boolean unlockAccount(String email, String newpass) {
		// TODO Auto-generated method stub
	ResponseEntity<UserEntity> user = userRepo.findByEmail(email);
	if(user.getBody()!=null) {
		UserEntity userbody = user.getBody();
		userbody.setIsActive("Active");
		return true;
	}	
	
		
		return false;
	}

	@Override
	public String forgetPass(String email) {
		// TODO Auto-generated method stub
		
		return null;
	}
	
	

}
