package com.routemaster.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.routemaster.exception.BusException;
import com.routemaster.exception.FeedBackException;
import com.routemaster.exception.UserException;
import com.routemaster.model.Feedback;
import com.routemaster.service.FeedbackService;

import jakarta.validation.Valid;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/routemaster")
public class FeedbackController {

	@Autowired
	private FeedbackService feedbackService;
	
	@PostMapping("/user/feedback/add/{busId}")
	public ResponseEntity<Feedback> addFeedback(@Valid @RequestBody Feedback feedback,
			@PathVariable("busId") Integer busId,
			@RequestParam(required = false) String key) throws UserException,BusException{
		
		Feedback feedback2 = feedbackService.addFeedback(feedback, busId, key);
		
		return new ResponseEntity<Feedback>(feedback2,HttpStatus.ACCEPTED);
	}
	
	@PutMapping("/user/feedback/update")
	public ResponseEntity<Feedback> updateFeedback(@Valid @RequestBody Feedback feedback,@RequestParam(required = false) String key) throws FeedBackException,UserException{
		
		Feedback feedback2 = feedbackService.updateFeedback(feedback, key);
		
		return new ResponseEntity<Feedback>(feedback2,HttpStatus.ACCEPTED);
	}
	
	@DeleteMapping("/user/feedback/delete/{id}")
	public ResponseEntity<Feedback> deleteFeedback(@PathVariable("id") Integer feedbackId,@RequestParam(required = false) String key) throws FeedBackException,UserException{
		Feedback feedback = feedbackService.deleteFeedback(feedbackId, key);
		return new ResponseEntity<Feedback>(feedback,HttpStatus.ACCEPTED);
	}
	
	//view Feedback
	
	@GetMapping("/feedback/{id}")
	public ResponseEntity<Feedback> viewFeedback(@PathVariable("id") Integer ID) throws FeedBackException{
		
		Feedback feedback = feedbackService.viewFeedback(ID);
		
		return new ResponseEntity<Feedback>(feedback,HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/feedback/all")
	public ResponseEntity<List<Feedback>> viewFeedbackAll() throws FeedBackException{
		
		List<Feedback> feedback = feedbackService.viFeedbackAll();
		
		return new ResponseEntity<List<Feedback>>(feedback,HttpStatus.ACCEPTED);
		
	}
	
	@GetMapping("/feedback/count")
	public ResponseEntity<Integer> getFeedbBackCount(){
		return new ResponseEntity<Integer>(feedbackService.getAllFeedBackCount(),HttpStatus.OK);
	}
}
