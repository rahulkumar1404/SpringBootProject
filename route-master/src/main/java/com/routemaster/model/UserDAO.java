package com.routemaster.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDAO {
	@NotNull(message = "Name cannot be null!")
	@NotBlank(message = "Name cannot be null!")
	private String firstName;
	private String lastName;
	
	@NotNull(message = "Mobile cannot be null!")
	@NotBlank(message = "Mobile cannot be null!")
	@Size(min = 10,max = 10)
	private String mobile;
	
	@Email
	private String email;
	
//	@JsonIgnore
	@NotNull(message = "Password cannot be null!")
	@NotBlank(message = "Password cannot be null!")
	@Pattern(regexp = "[A-Za-z0-9!@#$%^&*_]{8,15}",message = "Password must be 8-15 characters including alphanumerics and special characters")
	private String password;
	
}
