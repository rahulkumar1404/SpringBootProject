package com.routemaster.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.routemaster.model.Route;

@Repository
public interface RouteRepository extends JpaRepository<Route, Integer>{
	public Route findByRouteFromAndRouteTo(String from,String to);
}
