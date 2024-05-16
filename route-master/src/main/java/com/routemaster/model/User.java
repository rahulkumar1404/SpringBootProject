package com.routemaster.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer userID;
	
	private String firstName;
	private String lastName;
	private String mobile;
	private String email;
	private String password;
	private Integer totalFeedBack = 0;
	private Integer totalReservation = 0;
	 
	@JsonIgnore
	@OneToMany(mappedBy = "user" , cascade = CascadeType.ALL)
	private List<Reservation> reservationList = new ArrayList<>();
	
	@JsonIgnore
	@OneToMany(mappedBy = "user")
	private List<Feedback> feedbackList = new ArrayList<>();
	

}
