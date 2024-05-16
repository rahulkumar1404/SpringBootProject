package com.routemaster.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.routemaster.exception.AdminException;
import com.routemaster.exception.UserException;
import com.routemaster.model.CurrentAdminSession;
import com.routemaster.model.CurrentUserSession;
import com.routemaster.model.User;
import com.routemaster.model.UserDAO;
import com.routemaster.repository.CurrentAdminSessionRepository;
import com.routemaster.repository.CurrentUserSessionRepository;
import com.routemaster.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private CurrentUserSessionRepository userSessionRepository;
	
	@Autowired
	private CurrentAdminSessionRepository adminSession;
	
	@Override
	public User createUser(User user) throws UserException {
		User registeredUser = userRepository.findByEmail(user.getMobile());
		if(registeredUser != null)
			throw new UserException("User is already registered!");
		return userRepository.save(user);
	}

	@Override
	public User updateUser(UserDAO userDAO, String key) throws UserException {
		CurrentUserSession loggedInUserSession = userSessionRepository.findByUuid(key);
		if(loggedInUserSession == null)
			throw new UserException("Please enter a valid key or login first!");
		
		Optional<User> optional = userRepository.findById(loggedInUserSession.getUserID());
		
		if(optional.isEmpty())
			throw new UserException("User Not found!!!");
		
		User user = optional.get(); 
		user.setEmail(userDAO.getEmail());
		user.setFirstName(userDAO.getFirstName());
		user.setLastName(userDAO.getLastName());
		user.setMobile(userDAO.getMobile());
		
		return userRepository.save(user);
	}

	
	
	@Override
    public User deleteUser(Integer userID, String key) throws UserException, AdminException {
        CurrentAdminSession loggedInAdmin = adminSession.findByaid(key);
        if(loggedInAdmin == null)  throw new AdminException("Please enter a valid key or login first!");
        User user = userRepository.findById(userID).orElseThrow(() -> new UserException("Invalid user Id!"));
        userRepository.delete(user);
        return user;
    }

	@Override
	public User viewUserById(Integer userId, String key) throws UserException, AdminException {
		CurrentAdminSession loggedInAdminSession = adminSession.findByaid(key);
		if(loggedInAdminSession == null)
			throw new AdminException("Please enter a valid key or login first!");
		
		User user = userRepository.findById(userId).orElseThrow( ()-> new UserException("Invalid user Id!") );
		return user;
		
	}

	@Override
	public List<User> viewAllUser(String key) throws UserException, AdminException {
		CurrentAdminSession loggInAdminSession = adminSession.findByaid(key);
		if(loggInAdminSession == null)
			throw new AdminException("Please enter a valid key or login first!");
		List<User> list = userRepository.findAll();
		if (list.isEmpty()) {
			throw new UserException("No users found!");
		}
		return list;
	}

	@Override
	public Integer getAllUserCount() {
		return userRepository.findAll().size();
	}

}
