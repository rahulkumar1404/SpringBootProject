package com.routemaster.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class CurrentAdminSession {
	@Id
	@Column(unique = true)
	private Integer adminID;
	private String name;
	private String aid;
	private LocalDateTime time;
	
}
