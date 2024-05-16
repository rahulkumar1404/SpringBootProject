package com.routemaster.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.routemaster.model.Bus;

@Repository
public interface BusRepository extends JpaRepository<Bus, Integer>{
	public List<Bus> findByBusType(String busType);
	
	public Bus findByRouteFromAndRouteTo(String routeFrom, String routeTo);

}
