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

import com.routemaster.exception.AdminException;
import com.routemaster.exception.LoginException;
import com.routemaster.model.AdminLoginDTO;
import com.routemaster.model.CurrentAdminSession;
import com.routemaster.service.AdminLoginService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/routemaster")
public class AdminLoginController {

	@Autowired
	private AdminLoginService loginService;
	
	@PostMapping("/admin/login")
	public ResponseEntity<CurrentAdminSession> loginAdmin(@RequestBody AdminLoginDTO loginDTO) throws AdminException,LoginException{
		CurrentAdminSession currentAdminSession = loginService.adminLogin(loginDTO);
		return new ResponseEntity<>(currentAdminSession,HttpStatus.ACCEPTED);
	}
	
	@PostMapping("/admin/logout")
	public String logoutAdmin(@RequestParam(required = false) String key ) throws LoginException{
		return loginService.adminLogout(key);
	}
}
