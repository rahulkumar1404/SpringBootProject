package com.routemaster.service;

import com.routemaster.exception.AdminException;
import com.routemaster.model.Admin;

public interface AdminService {
	public Admin createAdmin(Admin admin) throws AdminException;
	public Admin updateAdmin(Admin admin, String key) throws AdminException;

}
