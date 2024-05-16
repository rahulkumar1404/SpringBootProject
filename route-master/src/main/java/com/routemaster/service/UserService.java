package com.routemaster.service;

import java.util.List;

import com.routemaster.exception.AdminException;
import com.routemaster.exception.UserException;
import com.routemaster.model.User;
import com.routemaster.model.UserDAO;

public interface UserService {
	public User createUser(User user) throws UserException;
	public User updateUser(UserDAO userDAO, String key) throws UserException;
	
	public User deleteUser(Integer userId,String key) throws UserException,AdminException;
	
	public User viewUserById(Integer userId,String key) throws UserException, AdminException;
	
	public List<User> viewAllUser(String key) throws UserException,AdminException;
	
	public Integer getAllUserCount();

}
