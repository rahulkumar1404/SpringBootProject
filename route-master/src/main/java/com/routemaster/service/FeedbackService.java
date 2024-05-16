package com.routemaster.service;

import java.util.List;

import com.routemaster.exception.BusException;
import com.routemaster.exception.FeedBackException;
import com.routemaster.exception.UserException;
import com.routemaster.model.Feedback;

public interface FeedbackService {
	public Feedback addFeedback(Feedback feedback,Integer busId,String key) throws BusException,UserException;
	
	public Feedback updateFeedback(Feedback feedback,String key) throws FeedBackException, UserException;
	
	public Feedback deleteFeedback(Integer feedbackId, String key) throws FeedBackException, UserException;
	
	public Feedback viewFeedback(Integer id) throws FeedBackException;
	
	public List<Feedback> viFeedbackAll() throws FeedBackException;
	
	public Integer getAllFeedBackCount();
}
