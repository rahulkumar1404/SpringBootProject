package com.routemaster.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.routemaster.exception.ReservationException;
import com.routemaster.model.Reservation;
import com.routemaster.model.ReservationDTO;

@Service
public interface ReservationService {
	
	public Reservation addReservation(ReservationDTO dto,String key) throws ReservationException;
	
	public Reservation viewReservation(Integer rid,String key) throws ReservationException;
	
	public List<Reservation> getAllReservation(String key) throws ReservationException;
	
	public List<Reservation> viewReservationByUserId(Integer uid,String key) throws ReservationException;
	
	public Reservation deleteReservation(Integer rid,String key) throws ReservationException;
	
	public Reservation updateReservation(Integer rid,ReservationDTO dto,String key) throws ReservationException;

	public Integer getReservationCount();
}
