package com.routemaster.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.routemaster.model.Admin;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Integer>{
	public Admin findByEmail(String email);
}
