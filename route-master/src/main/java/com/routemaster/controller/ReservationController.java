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

import com.routemaster.exception.ReservationException;
import com.routemaster.model.Reservation;
import com.routemaster.model.ReservationDTO;
import com.routemaster.service.ReservationService;

import jakarta.validation.Valid;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/routemaster")
public class ReservationController {

	@Autowired
	private ReservationService service;
	
	@PostMapping("/user/reservation/add")
	public ResponseEntity<Reservation> addReservation(@Valid @RequestBody ReservationDTO dto, @RequestParam(required = false) String key) throws ReservationException{
		Reservation reservation = service.addReservation(dto, key);
		
		return new ResponseEntity<>(reservation,HttpStatus.CREATED);
	}
	
	@PutMapping("/user/reservation/update/{rid}")
	public ResponseEntity<Reservation> updateReservation(@Valid @RequestBody ReservationDTO dto, @RequestParam(required = false) String key ,@PathVariable Integer rid) throws ReservationException{
		Reservation reservation = service.updateReservation(rid, dto, key);
		
		return new ResponseEntity<>(reservation,HttpStatus.OK);
		
	}
	
	@DeleteMapping("/user/reservation/delete/{rid}")
	public ResponseEntity<Reservation> deleteReservation(@RequestParam(required = false) String key, @PathVariable Integer rid ) throws ReservationException{
		
		Reservation reservation = service.deleteReservation(rid, key);
		
		return new ResponseEntity<>(reservation,HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/reservation/{rid}")
	public ResponseEntity<Reservation> viewReservationById(@PathVariable Integer rid, @RequestParam(required = false) String key ) throws ReservationException{
		
		Reservation reservation = service.viewReservation(rid, key);
		
		return new ResponseEntity<>(reservation,HttpStatus.FOUND);
	}
	
	@GetMapping("/reservation/all")
	public ResponseEntity<List<Reservation>> viewAllReservation(@RequestParam(required = false) String key ) throws ReservationException{
		
		List<Reservation> reservations = service.getAllReservation(key);
		
		return new ResponseEntity<>(reservations,HttpStatus.FOUND);
	}
	
	@GetMapping("/user/reservation/{uid}")
	
	public ResponseEntity<List<Reservation>> viewReservationByUserId(@PathVariable Integer uid,@RequestParam(required = false) String  key) throws ReservationException{
		List<Reservation> reservations = service.viewReservationByUserId(uid, key);
		
		return new ResponseEntity<>(reservations,HttpStatus.FOUND);
	}
	
	@GetMapping("/reservation/count")
	public ResponseEntity<Integer> getReservationCount(){
		return new ResponseEntity<>(service.getReservationCount(),HttpStatus.OK);
	}
	
}
