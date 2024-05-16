package com.routemaster.service;

import java.util.List;

import com.routemaster.exception.AdminException;
import com.routemaster.exception.RouteException;
import com.routemaster.model.Route;

public interface RouteService {
	public Route addRoute(Route route,String key) throws RouteException, AdminException;
	public List<Route> viewAllRoutes() throws RouteException;
	public Route viewRoute(int routeId) throws RouteException;
	public Route updateRoute(Route route,String key) throws RouteException, AdminException;
	public Route deleteRoute(int routeID,String key) throws RouteException,AdminException;
	
	public Integer getRouteCount();
	
}
