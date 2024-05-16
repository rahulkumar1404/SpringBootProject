package com.routemaster.model;

import java.time.LocalDate;
import java.time.LocalTime;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Reservation {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer reservationID;
	private String status;
	private LocalDate date;
	private LocalTime time;
	
	private String source;
	private String destination;
	private LocalDate journeyDate;
	private Integer bookedSeat;
	private Integer fare;
	
	@JsonIgnore
	@ManyToOne
	private User user;
	
	@JsonIgnore
	@ManyToOne
	private Bus bus;

}
