package com.routemaster.model;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReservationDTO {
	@NotNull(message = "Source required to book a reservation")
	@NotBlank(message = "Source should not be blanked")
	private String source;
	
	@NotNull(message = "Destination required to book a reservation")
	@NotBlank(message = "Destination should not be blanked")
	private String destination;
	
	
	
	@NotNull
//	@JsonFormat(pattern = "yyyy-MM-dd")
	@DateTimeFormat(iso = ISO.DATE)
	private LocalDate journeyDate;
	
	@Min(1)
	private Integer bookedSeat;
	

}
