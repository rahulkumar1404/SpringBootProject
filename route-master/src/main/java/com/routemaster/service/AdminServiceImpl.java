package com.routemaster.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.routemaster.exception.AdminException;
import com.routemaster.model.Admin;
import com.routemaster.model.CurrentAdminSession;
import com.routemaster.repository.AdminRepository;
import com.routemaster.repository.CurrentAdminSessionRepository;

@Service
public class AdminServiceImpl implements AdminService{

	@Autowired
	private AdminRepository adminRepository;
	
	@Autowired
	private CurrentAdminSessionRepository adminSessionRepository;
	
	
	@Override
	public Admin createAdmin(Admin admin) throws AdminException {
		Admin a = adminRepository.findByEmail(admin.getEmail());
		if(a != null) throw new AdminException("Admin is already register with email ( "+a.getEmail());
		return adminRepository.save(admin);
	}

	@Override
	public Admin updateAdmin(Admin admin, String key) throws AdminException {
		CurrentAdminSession adminSession =adminSessionRepository.findByaid(key);
		if(adminSession == null) throw new AdminException("Please enter valid key or Login first");
 
		Optional<Admin> upateUserOpt =  adminRepository.findById(adminSession.getAdminID());
		if(upateUserOpt.isPresent()) {
					Admin updateAdmin = upateUserOpt.get();
					updateAdmin.setName(admin.getName());
					updateAdmin.setEmail(admin.getEmail());
					updateAdmin.setPassword(admin.getPassword());
					return adminRepository.save(updateAdmin);
				}else {
					throw new AdminException("Invalid Admin details, please login for updating admin");
				}
	}

}
