package com.routemaster.model;

import com.routemaster.model.Route;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Bus {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer busId;

	@NotBlank(message = "Bus name can't be null/blank, Please provide a valid name first!")
	private String busName;
	

	@NotBlank(message = "Driver name can't be null/blank, Please provide a valid name first!")
	private String driverName;
	
	@NotBlank(message = "Bus Type can't be null/blank, Please provide a valid bus type")
	private String busType;
	
	@NotBlank(message = "Choose a valid starting point.")
	private String routeFrom;
	
	@NotBlank(message = "Choose a valid destination.")
	private String routeTo;
	
	@NotNull(message = "Bus Journey Date can't be null, Please provide correct date")
	@DateTimeFormat(iso = ISO.DATE)
	private LocalDate busJourneyDate;

	@DateTimeFormat(iso = ISO.TIME)
	private LocalTime arrivalTime;
	

	@DateTimeFormat(iso = ISO.TIME)
	private LocalTime departureTime;
	
	@Column(name = "total_seats")
	private Integer seats;
	
	private Integer availableSeats;
	
	@NotNull(message = "fare can't be null")
	private Integer fare;
	
//	@JsonIgnore
	@ManyToOne(cascade = CascadeType.ALL)
	private Route route;
	
	@JsonIgnore
	@OneToMany(mappedBy = "bus",cascade = CascadeType.ALL)
	private List<Reservation> reservationList = new ArrayList<>();
	

}
