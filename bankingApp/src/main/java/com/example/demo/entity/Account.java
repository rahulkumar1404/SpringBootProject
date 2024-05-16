package com.example.demo.entity;

import jakarta.persistence.Entity;
import lombok.Data;

@lombok.Data
@lombok.AllArgsConstructor
@jakarta.persistence.Table(name="accounts")
@lombok.Getter
@lombok.Setter
@lombok.NoArgsConstructor
@Entity
public class Account {
	@jakarta.persistence.Id
	@jakarta.persistence.GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
	private Long id;
	
	@jakarta.persistence.Column(name = "account_holder_name")
	private String accountHolderName;
	private double balance;
}
