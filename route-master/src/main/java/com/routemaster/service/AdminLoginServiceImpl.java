package com.routemaster.service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.routemaster.exception.AdminException;
import com.routemaster.exception.LoginException;
import com.routemaster.model.Admin;
import com.routemaster.model.AdminLoginDTO;
import com.routemaster.model.CurrentAdminSession;
import com.routemaster.repository.AdminRepository;
import com.routemaster.repository.CurrentAdminSessionRepository;

@Service
public class AdminLoginServiceImpl implements AdminLoginService{
	
	@Autowired
	private CurrentAdminSessionRepository adminSessionRepository;
	
	@Autowired
	private AdminRepository adminRepository;
	
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
	public CurrentAdminSession adminLogin(AdminLoginDTO loginDTO) throws LoginException, AdminException {
		Admin registeredAdmin = adminRepository.findByEmail(loginDTO.getEmail());
		
		if(registeredAdmin == null) throw new AdminException("Email is not registed!!!");
		
		if(registeredAdmin.getPassword().equals(loginDTO.getPassword())) {
			Optional<CurrentAdminSession> loggedInAdmin = adminSessionRepository.findById(registeredAdmin.getAdminID());
			if(loggedInAdmin.isPresent()) throw new LoginException("Admin is already logged in!");
			
			String key = generateRandomCode(10);
			CurrentAdminSession adminSession = new CurrentAdminSession();
			adminSession.setAdminID(registeredAdmin.getAdminID());
			adminSession.setName(registeredAdmin.getName());
			adminSession.setAid(key);
			adminSession.setTime(LocalDateTime.now());
			return adminSessionRepository.save(adminSession);
		}
		
		else 
			throw new LoginException("Password is Wrong!!!");
	}

	@Override
	public String adminLogout(String key) throws LoginException {
		CurrentAdminSession currentAdminSession = adminSessionRepository.findByaid(key);
		if(currentAdminSession == null) throw new LoginException("Admin not logged In!");
		adminSessionRepository.delete(currentAdminSession);
		return "Admin logged out!";
	}

}
