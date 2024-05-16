package com.routemaster.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.routemaster.exception.LoginException;
import com.routemaster.model.CurrentUserSession;
import com.routemaster.model.UserLoginDTO;
import com.routemaster.service.UserLoginService;

import jakarta.validation.Valid;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/routemaster")
public class UserLoginController {

	@Autowired
	private UserLoginService userLoginService;
	
	@PostMapping("/user/login")
	public ResponseEntity<CurrentUserSession> loggedInUser(@Valid @RequestBody UserLoginDTO loginDTO) throws LoginException{
		CurrentUserSession currentUserSession = userLoginService.userLogin(loginDTO);
		
		return new ResponseEntity<CurrentUserSession>(currentUserSession,HttpStatus.ACCEPTED);
	}
	
	@PostMapping("/user/logout")
	public String logoutUser(@RequestParam(required =  false) String key) throws LoginException{
		return userLoginService.userLogout(key);
	}
}
