package com.routemaster.service;


import com.routemaster.exception.AdminException;
import com.routemaster.exception.LoginException;
import com.routemaster.model.AdminLoginDTO;
import com.routemaster.model.CurrentAdminSession;

public interface AdminLoginService {
	public CurrentAdminSession adminLogin(AdminLoginDTO loginDTO) throws LoginException, AdminException;
	public String adminLogout(String key) throws LoginException;

}
