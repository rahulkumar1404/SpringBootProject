package com.routemaster.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.routemaster.exception.AdminException;
import com.routemaster.exception.RouteException;
import com.routemaster.model.Bus;
import com.routemaster.model.CurrentAdminSession;
import com.routemaster.model.Route;
import com.routemaster.repository.AdminRepository;
import com.routemaster.repository.CurrentAdminSessionRepository;
import com.routemaster.repository.RouteRepository;

@Service
public class RouteServiceImpl implements RouteService{

	@Autowired
	private RouteRepository routeRepository;
	
	@Autowired
	private CurrentAdminSessionRepository currentAdminSessionRepository;
	
	@Autowired
	private AdminRepository adminRepository;
	
	
	@Override
	public Route addRoute(Route route, String key) throws RouteException, AdminException {
		
		CurrentAdminSession loggedInAdmin = currentAdminSessionRepository.findByaid(key);
		
		if(loggedInAdmin == null)
			throw new AdminException("Please provide a valid id to add route !");
		
		Route newRoute = (Route) routeRepository.findByRouteFromAndRouteTo(route.getRouteFrom(), route.getRouteTo());
		
		if(newRoute != null)
			throw new RouteException("Route :"+newRoute.getRouteFrom()+" to "+newRoute.getRouteTo()+" is already present !");
		
		List<Bus> buses = new ArrayList<>();
		
		if(route != null) {
			route.setBusList(buses);
			return routeRepository.save(route);
		}
		else{
			throw new RouteException("This root is not available");
		}
		
	}

	@Override
	public List<Route> viewAllRoutes() throws RouteException {
		List<Route> routes = routeRepository.findAll();
		
		if(routes.isEmpty()) {
			throw new RouteException("No route available");
		}
		return routes;
	}

	@Override
	public Route viewRoute(int routeId) throws RouteException {
		Optional<Route> optional = routeRepository.findById(routeId);
		
		return optional.orElseThrow( ()-> new RouteException("There is no route present of this routeId :"+ routeId) );
	}

	@Override
	public Route updateRoute(Route route, String key) throws RouteException, AdminException {
		
		CurrentAdminSession loggedInAdmin = currentAdminSessionRepository.findByaid(key);
		if(loggedInAdmin == null)
			throw new AdminException("Please provide a valid id to add route !");
		
		Optional<Route> existedRoute = routeRepository.findById(route.getRouteID());
		
		if(existedRoute.isPresent()) {
			Route presentRoute = existedRoute.get();
			List<Bus> busList = presentRoute.getBusList();
			
			if(!busList.isEmpty())
				throw new RouteException("Cannot update running route! Buses are already scheduled in the route.");
			
			return routeRepository.save(route);
		}else {
			throw new RouteException("Route doesn't exist of this routeId : "+route.getRouteID());
		}
	}

	@Override
	public Route deleteRoute(int routeID, String key) throws RouteException, AdminException {
		
		CurrentAdminSession loggedInAdmin = currentAdminSessionRepository.findByaid(key);
		
		if(loggedInAdmin == null)
			throw new AdminException("Please provide a valid id to add route !");
		
		Optional<Route> route = routeRepository.findById(routeID);
		
		if(route.isPresent()) {
			Route existingRoute = route.get();
			routeRepository.delete(existingRoute);
			return existingRoute;
		}
		else {
			throw new RouteException("There is no route of this routeId : "+routeID);
		}
	}

	@Override
	public Integer getRouteCount() {
		return routeRepository.findAll().size();
	}

}
