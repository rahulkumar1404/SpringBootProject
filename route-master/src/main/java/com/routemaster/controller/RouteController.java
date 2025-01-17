package com.routemaster.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.routemaster.exception.AdminException;
import com.routemaster.exception.RouteException;
import com.routemaster.model.Route;
import com.routemaster.service.RouteService;

import jakarta.validation.Valid;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/routemaster")
public class RouteController {
	
	@Autowired
	private RouteService routeService;
	
	@PostMapping("/admin/route/add")
	public ResponseEntity<Route> addRoute(@Valid @RequestBody Route route, @RequestParam(required = false) String key) throws RouteException,AdminException{
		
		Route newRoute = routeService.addRoute(route, key);
		
		return new ResponseEntity<Route>(newRoute,HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/route/all")
	public ResponseEntity<List<Route>> getAllRouteHandler() throws RouteException{
		List<Route> routes = routeService.viewAllRoutes();
		return new ResponseEntity<>(routes,HttpStatus.OK);
	}
	
	@GetMapping("/route/{routeID}")
	public ResponseEntity<Route> getAllRouteByRouteIdHandler(@PathVariable Integer routeID) throws RouteException{
		
		Route route = routeService.viewRoute(routeID);
		
		return new ResponseEntity<>(route,HttpStatus.OK);
	}
	
	@PutMapping("/admin/route/update")
	public ResponseEntity<Route> updateRoute(@Valid @RequestBody Route route, @RequestParam(required = false) String key) throws RouteException,AdminException{
		Route newRoute = routeService.updateRoute(route, key);
		return new ResponseEntity<Route>(newRoute,HttpStatus.OK);
	}
	
	@DeleteMapping("/admin/route/delete/{routeID}")
	public ResponseEntity<Route> DeleteRoute(@PathVariable Integer routeID, @RequestParam(required = false) String key) throws RouteException,AdminException{
		
		Route route = routeService.deleteRoute(routeID, key);
		
		return new ResponseEntity<Route>(route,HttpStatus.OK);
	}
	
	@GetMapping("/route/count")
	public ResponseEntity<Integer> getRouteCount(){
		return new ResponseEntity<>(routeService.getRouteCount(),HttpStatus.OK);
	}
	

}
