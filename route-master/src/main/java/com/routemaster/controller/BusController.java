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
import com.routemaster.exception.BusException;
import com.routemaster.model.Bus;
import com.routemaster.service.BusService;

import jakarta.validation.Valid;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/routemaster")
public class BusController {
	
	@Autowired
	private BusService busService;
	
	//admin endpoints
	@PostMapping("/admin/bus/add")
	public ResponseEntity<Bus> addBusHandler(@Valid @RequestBody Bus bus,@RequestParam(required = false) String key) throws BusException,AdminException{
		Bus newBus = busService.addBus(bus, key);
		return new ResponseEntity<>(newBus,HttpStatus.CREATED);
	}
	
	@PutMapping("/admin/bus/update")
	public ResponseEntity<Bus> updateBusHAndler(@Valid @RequestBody Bus bus,@RequestParam(required = false) String key) throws BusException,AdminException{
		Bus newBus = busService.updateBus(bus, key);
		return new ResponseEntity<>(newBus,HttpStatus.OK);
		
	}
	
	@DeleteMapping("/admin/bus/delete/{busId}")
	public ResponseEntity<Bus> deleteBusByBusHandler(@PathVariable("busId") Integer busId,@RequestParam(required = false) String key) throws BusException,AdminException{
		Bus deletedBus = busService.deleteBus(busId, key);
		return new ResponseEntity<>(deletedBus,HttpStatus.OK);
	}
	
	//shared endpoints (user and admin both)
	@GetMapping("/bus")
	public ResponseEntity<List<Bus>> getAllBusHandler() throws BusException{
		List<Bus> allBuses = busService.viewAllBuses();
		return new ResponseEntity<>(allBuses,HttpStatus.OK);
	}
	
	@GetMapping("/bus/{busId}")
	public ResponseEntity<Bus> getBusByIdHandler(@PathVariable("busId") Integer busId) throws BusException{
		Bus bus = busService.viewBus(busId);
		return new ResponseEntity<>(bus,HttpStatus.OK);
	}
	
	@GetMapping("/bus/type/{busType}")
	public ResponseEntity<List<Bus>> getBusesByBusTypeHandler(@PathVariable("busType") String busType) throws BusException{
		List<Bus> busList = busService.viewBusByBusType(busType);
		return new ResponseEntity<>(busList,HttpStatus.OK);
	}
	
	@GetMapping("/bus/count")
	public ResponseEntity<Integer> getBusCount(){
		Integer total = busService.getTotalBusCount();
		return new ResponseEntity<>(total,HttpStatus.OK);
	}

}





//format for sending post request
//{
//     "busName": "16thBus",
//     "driverName": "rahul",
//     "busType": "ac",
//     "routeFrom": "mumbai",
//     "routeTo": "lucknow",
//     "busJourneyDate": "2023-05-08",
//     "arrivalTime":"09:00:00",
//     "departureTime":"12:00:00",
//     "seats": 35,
//     "availableSeats": 35,
//     "fare": 350,
//     "route": {
//     "routeFrom": "a",
//     "routeTo": "b",
//     "distance": 2000
//     }
//     }
