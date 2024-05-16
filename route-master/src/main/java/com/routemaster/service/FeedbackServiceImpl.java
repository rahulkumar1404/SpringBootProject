package com.routemaster.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.routemaster.exception.BusException;
import com.routemaster.exception.FeedBackException;
import com.routemaster.exception.UserException;
import com.routemaster.model.Bus;
import com.routemaster.model.CurrentUserSession;
import com.routemaster.model.Feedback;
import com.routemaster.model.User;
import com.routemaster.repository.BusRepository;
import com.routemaster.repository.CurrentUserSessionRepository;
import com.routemaster.repository.FeedbackRepository;
import com.routemaster.repository.UserRepository;

@Service
public class FeedbackServiceImpl implements FeedbackService{

	@Autowired
	private FeedbackRepository fdao;
	
	@Autowired
	private UserRepository userDao;
	
	@Autowired 
	private BusRepository busDao;
	
	@Autowired
	private CurrentUserSessionRepository userSessionDao;
	
	
	@Override
	public Feedback addFeedback(Feedback feedback, Integer busId, String key) throws BusException, UserException {

		CurrentUserSession loggedInUser = userSessionDao.findByUuid(key);
		
		if(loggedInUser == null)
			throw new UserException("Please provide a valid key to give Feedback!");
		
		User user = userDao.findById(loggedInUser.getUserID()).orElseThrow( ()-> new UserException("User not found!") );
		user.setTotalFeedBack(user.getTotalFeedBack() + 1);
		
		Optional<Bus> busOptional = busDao.findById(busId);
		if(busOptional.isEmpty()) {
			throw new BusException("Bus is not present with Id: "+busId);
		}
		
		user.getFeedbackList().add(feedback);
		feedback.setBusId(busId);
		feedback.setUserId(user.getUserID());
		feedback.setUser(user);
		feedback.setFeedbackDateTime(LocalDateTime.now());
		Feedback savedFeedback = fdao.save(feedback);
		userDao.save(user);
		return savedFeedback;
	}

	@Override
	public Feedback updateFeedback(Feedback feedback, String key) throws FeedBackException, UserException {
CurrentUserSession loggedInUser = userSessionDao.findByUuid(key);
		
		if(loggedInUser == null)
			throw new UserException("Please provide a valid key to update Feedback!");
		
		User user = userDao.findById(loggedInUser.getUserID()).orElseThrow( ()-> new UserException("User not found!") );
		
		Optional<Feedback> opt = fdao.findById(feedback.getFeedBackId());
		
		if(opt.isPresent()) {
			Feedback feedback2 = opt.get();
			Optional<Bus> busOptional = busDao.findById(feedback2.getBusId());
			
			if(!busOptional.isPresent())
				throw new FeedBackException("Invalid bus details!");
			
			feedback.setUser(user);
			user.getFeedbackList().add(feedback);
			feedback.setFeedbackDateTime(LocalDateTime.now());
			return fdao.save(feedback);
		}else 
			throw new FeedBackException("No feedback found!");
		
		
	}

	@Override
	public Feedback deleteFeedback(Integer feedbackId, String key) throws FeedBackException, UserException {
		
		CurrentUserSession loggedInUser = userSessionDao.findByUuid(key);
		
		if(loggedInUser == null)
			throw new UserException("Please provide a valid key to update Feedback!");
		
		User user = userDao.findById(loggedInUser.getUserID()).orElseThrow( ()->new UserException("User not found!") );
		user.setTotalFeedBack(user.getTotalFeedBack() - 1);
		
		Optional<Feedback> fedOptional = fdao.findById(feedbackId);
		
		if(fedOptional.isPresent()) {
			Feedback existingFeedback = fedOptional.get();
			user.getFeedbackList().remove(existingFeedback);
			fdao.delete(existingFeedback);
			userDao.save(user);
			return existingFeedback;
		}
		else {
			throw new FeedBackException("No feedback found!");
		}
	}

	@Override
	public Feedback viewFeedback(Integer id) throws FeedBackException {
		Optional<Feedback> fedOptional = fdao.findById(id);
		if(fedOptional.isPresent()) {
			return fedOptional.get();
		}
		throw new FeedBackException("No feedback found!");
	}

	@Override
	public List<Feedback> viFeedbackAll() throws FeedBackException {
		Optional<List<Feedback>> fedOptional = Optional.of(fdao.findAll());
		if(fedOptional.isPresent()) {
			return fedOptional.get();
		}
		throw new FeedBackException("No feedbacks found!");
	}

	@Override
	public Integer getAllFeedBackCount() {
		return fdao.findAll().size();
	}
	

}
