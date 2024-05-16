package com.routemaster.service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.routemaster.exception.LoginException;
import com.routemaster.model.CurrentUserSession;
import com.routemaster.model.User;
import com.routemaster.model.UserLoginDTO;
import com.routemaster.repository.CurrentUserSessionRepository;
import com.routemaster.repository.UserRepository;

@Service
public class UserLoginServiceImpl implements UserLoginService{

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private CurrentUserSessionRepository userSessionRepository;
	private static String generateRandomCode(int length) {
		String alphanumericSet = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
		Random random = new Random();
		StringBuilder codeBuilder = new StringBuilder(length);
		
		for(int i =0;i<length;i++) {
			int index = random.nextInt(alphanumericSet.length());
			char randomChar = alphanumericSet.charAt(index);
			codeBuilder.append(randomChar);
		}
		return codeBuilder.toString();
	}
	
	@Override
	public CurrentUserSession userLogin(UserLoginDTO userLoginDTO) throws LoginException {
		User registeredUser = userRepository.findByEmail(userLoginDTO.getEmail());
		if(registeredUser == null)
			throw new LoginException("Please enter valid email!");
		
		Optional<CurrentUserSession> loggedInUser = userSessionRepository.findById(registeredUser.getUserID());
//				userRepository.findById(registeredUser.getUserID());
		
		if(loggedInUser.isPresent())
			throw new LoginException("User already Logged!");
		
		
		if(registeredUser.getPassword().equals(userLoginDTO.getPassword())) {
			String key = generateRandomCode(10);  //random string
			
			CurrentUserSession currentUserSession = new CurrentUserSession();
			
			currentUserSession.setUserID(registeredUser.getUserID());
			currentUserSession.setUuid(key);
			currentUserSession.setTime(LocalDateTime.now());
			return userSessionRepository.save(currentUserSession);
		}else {
			throw new LoginException("Please enter a valid password!");
		}
	}

	@Override
	public String userLogout(String key) throws LoginException {
		
		CurrentUserSession loggedInUser = userSessionRepository.findByUuid(key);
		if(loggedInUser == null)
			throw new LoginException("Please enter a valid key or login first!");
		
		return "User logged out!";
	}

}
