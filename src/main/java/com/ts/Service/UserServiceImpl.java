package com.ts.Service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.sun.el.stream.Stream;
import com.ts.entities.CityEntity;
import com.ts.entities.CountryEntity;
import com.ts.entities.StateEntity;
import com.ts.entities.UserEntity;
import com.ts.repository.CityEntityRepository;
import com.ts.repository.CountryEntityRepository;
import com.ts.repository.StateEntityRepository;
import com.ts.repository.UserEntityRepository;
import com.ts.utils.EmailService;

@Service
public class UserServiceImpl  implements UserService{
	
	@Autowired
	private UserEntityRepository userRepo;
	
	@Autowired
	private CountryEntityRepository countryRepo;
	
	@Autowired
	private StateEntityRepository stateRepo;
	
	@Autowired
	private CityEntityRepository cityRepo;
	
	@Autowired
	private EmailService emailService;
	
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
		UserEntity entity = userRepo.findByEmail(email);

		if(entity!=null) {
			return false;
		}
		return true;
	}

	@Override
	public boolean saveUser(UserEntity user) throws Exception {
		// TODO Auto-generated method stub
		UserEntity userEntity = userRepo.save(user);
		
		String to = user.getEmail();
		String body = getUnlockAccEmailBody(user);
		String subject= "User Registration Successful";
		
		boolean msgString = emailService.sendMail(subject, body, to); 
		if(msgString) {
			return true;
		}
		
		return false;
	}

	@Override
	public boolean loginCheck(String email, String password) {
		// TODO Auto-generated method stub
		UserEntity byEmailAndPassword = userRepo.findByEmailAndPassword(email, password);

		if (byEmailAndPassword != null){
			return true;
		}
		return false;
	}

	@Override
	public boolean isTempValid(String email, String password) {
		UserEntity byEmailAndPassword = userRepo.findByEmailAndPassword(email, password);

		if (byEmailAndPassword != null){
			return true;
		}
		return false;
	}

	@Override
	public boolean unlockAccount(String email, String newpass) {
		// TODO Auto-generated method stub
		UserEntity entity = userRepo.findByEmail(email);
		if(entity!=null) {
			entity.setIsActive("ACTIVE");
			entity.setPassword(newpass);
			return true;
		}
		
		return false;
	}

	@Override
	public String forgetPass(String email) {
		// TODO Auto-generated method stub
		
		return null;
	}

	@Override
	public String sendEmail(UserEntity user) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public String getUnlockAccEmailBody(UserEntity user) {
		StringBuffer sb = new StringBuffer("");
		String body = null;
		try {
			File f = new File("unlock-acc-email-body.txt");
			FileReader fr = new FileReader(f);
			BufferedReader br = new BufferedReader(fr);
			String line = br.readLine();
			while (line != null) {
				sb.append(line);
				line = br.readLine();
			}
			br.close();
			body = sb.toString();
			body = body.replace("{FNAME}", user.getFirstName());
			body = body.replace("{LNAME}", user.getLastName());
			body = body.replace("{TEMP-PWD}", user.getPassword());
			body = body.replace("{EMAIL}", user.getEmail());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return body;
	}
	/*
	public String getRegSuccMailBody(UserEntity user) {
		String fileName = "unlock-acc-email-body.txt";
		List<String> replacedLines = null;
		String mailBody = null;
		try {
			Path path = Paths.get(fileName, "");
			Stream<String> lines = Files.lines(path);
			replacedLines = lines.map(line -> line.replace("{FNAME}", user.getFirstName())
											.replace("{LNAME}", user.getLastName())
											.replace("{TEMP-PWD}", user.getPassword())
											.replace("{EMAIL}", user.getEmailId()))
											.collect(Collectors.toList());
			mailBody = String.join("", replacedLines);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mailBody;
	}
*/
}
