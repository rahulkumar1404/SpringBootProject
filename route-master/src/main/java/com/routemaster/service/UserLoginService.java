package com.routemaster.service;

import com.routemaster.exception.LoginException;
import com.routemaster.model.CurrentUserSession;
import com.routemaster.model.UserLoginDTO;

public interface UserLoginService {
	public CurrentUserSession userLogin(UserLoginDTO userLoginDTO) throws LoginException;
	public String userLogout(String key) throws LoginException;
	

}
