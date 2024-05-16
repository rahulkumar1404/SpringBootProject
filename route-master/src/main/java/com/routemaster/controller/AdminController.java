package com.routemaster.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.routemaster.exception.AdminException;
import com.routemaster.model.Admin;
import com.routemaster.service.AdminService;

import jakarta.validation.Valid;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/routemaster")
public class AdminController {
	@Autowired
	private AdminService service;
	
	@PostMapping("/admin/register")
	public ResponseEntity<Admin> registerAdmin(@Valid @RequestBody Admin admin) throws AdminException {
		Admin savedAdmin = service.createAdmin(admin);
		
		return new ResponseEntity<>(savedAdmin,HttpStatus.CREATED);
	}
	
	@PutMapping("/admin/update")
	public ResponseEntity<Admin> updateAdmin(@Valid @RequestBody Admin admin,@RequestParam(required = false) String key) throws AdminException{
		Admin updateAdmin = service.updateAdmin(admin, key);
		return new ResponseEntity<>(updateAdmin,HttpStatus.OK);
	}

}
