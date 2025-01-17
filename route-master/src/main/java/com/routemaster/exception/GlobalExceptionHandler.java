package com.routemaster.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionHandler {
	@ExceptionHandler(Exception.class)
	public ResponseEntity<MyErrorDetails> exceptionHandler(Exception e, WebRequest w){
		MyErrorDetails details = new MyErrorDetails();
		details.setTime(LocalDateTime.now());
		details.setMessage(e.getMessage());
		details.setDetails(w.getDescription(false));
		return new ResponseEntity<>(details,HttpStatus.BAD_REQUEST);
	}
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<MyErrorDetails> MethodArgExceptionHandler(MethodArgumentNotValidException m){
		MyErrorDetails details = new MyErrorDetails();
		details.setTime(LocalDateTime.now());
		details.setMessage("Validattion Error");
		details.setDetails(m.getBindingResult().getFieldError().getDefaultMessage());
		return new ResponseEntity<>(details,HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(BusException.class)
	public ResponseEntity<MyErrorDetails> busExceptionHandler(BusException busEx,WebRequest webReq){
		MyErrorDetails error = new MyErrorDetails(LocalDateTime.now(),busEx.getMessage(),webReq.getDescription(false));
		return new ResponseEntity<>(error,HttpStatus.BAD_REQUEST);
	}
	@ExceptionHandler(AdminException.class)
	public ResponseEntity<MyErrorDetails> adminExceptionHandler(AdminException e, WebRequest w){
		MyErrorDetails details = new MyErrorDetails();
		details.setTime(LocalDateTime.now());
		details.setMessage(e.getMessage());
		details.setDetails(w.getDescription(false));
		return new ResponseEntity<>(details,HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(LoginException.class)
	public ResponseEntity<MyErrorDetails> loginExceptionHandler(LoginException e, WebRequest w){
		MyErrorDetails details = new MyErrorDetails();
		details.setTime(LocalDateTime.now());
		details.setMessage(e.getMessage());
		details.setDetails(w.getDescription(false));
		return new ResponseEntity<>(details,HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(ReservationException.class)
	public ResponseEntity<MyErrorDetails> reservationExceptionHandler(ReservationException e, WebRequest w){
		MyErrorDetails details = new MyErrorDetails();
		details.setTime(LocalDateTime.now());
		details.setMessage(e.getMessage());
		details.setDetails(w.getDescription(false));
		return new ResponseEntity<>(details,HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(UserException.class)
	public ResponseEntity<MyErrorDetails> userExceptionHandler(UserException e, WebRequest w){
		MyErrorDetails details = new MyErrorDetails();
		details.setTime(LocalDateTime.now());
		details.setMessage(e.getMessage());
		details.setDetails(w.getDescription(false));
		return new ResponseEntity<>(details,HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(RouteException.class)
	public ResponseEntity<MyErrorDetails> routeExceptionHandler(RouteException e, WebRequest w){
		MyErrorDetails details = new MyErrorDetails();
		details.setTime(LocalDateTime.now());
		details.setMessage(e.getMessage());
		details.setDetails(w.getDescription(false));
		return new ResponseEntity<>(details,HttpStatus.BAD_REQUEST);
	}
	
	

}
