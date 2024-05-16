package com.routemaster.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class CurrentUserSession {
	@Id
	@Column(unique = true)
	private Integer userID;
	private String uuid;
	private LocalDateTime time;
}
