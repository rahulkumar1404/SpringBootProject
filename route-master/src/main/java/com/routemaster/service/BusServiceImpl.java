package com.routemaster.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.routemaster.exception.AdminException;
import com.routemaster.exception.BusException;
import com.routemaster.model.Bus;
import com.routemaster.model.CurrentAdminSession;
import com.routemaster.model.Route;
import com.routemaster.repository.BusRepository;
import com.routemaster.repository.CurrentAdminSessionRepository;
import com.routemaster.repository.RouteRepository;

@Service
public class BusServiceImpl implements BusService{

	@Autowired
	private BusRepository busRepo;
	
	@Autowired
	private RouteRepository routeRepo;
	@Autowired
	private CurrentAdminSessionRepository currAdminRepo;
	
	//admin access method
	@Override
	public Bus addBus(Bus bus, String key) throws BusException, AdminException {
		CurrentAdminSession admin = currAdminRepo.findByaid(key);
		if(admin == null) {
			throw new AdminException("Key is not valid! Please provide a valid key.");
		}
		
		//finding and checking route
		Route route = new Route(bus.getRouteFrom(),bus.getRouteTo(),bus.getRoute().getDistance());
		if(route == null) 
			throw new BusException("Route is not valid");
		
		//adding route for this new bus
		bus.setRoute(route);
		
		//adding this new bus to the route
		route.getBusList().add(bus);
		
		//saving bus
		return busRepo.save(bus);
	}
	
	

	@Override
	public Bus updateBus(Bus bus, String key) throws BusException, AdminException {
		CurrentAdminSession admin = currAdminRepo.findByaid(key);
		if(admin == null) {
			throw new AdminException("Key is not valid! Please provide a valid key.");
		}
		Optional<Bus> bus1 = busRepo.findById(bus.getBusId());
		if(!bus1.isPresent()) {
			throw new BusException("Bus doesn't exit with busId: "+bus.getBusId());
		}
		
		Bus existBus = bus1.get();
		
		Route route =  routeRepo.findByRouteFromAndRouteTo(bus.getRouteFrom(), bus.getRouteTo());
		if(route == null) {
			Route route1 = new Route(bus.getRouteFrom(),bus.getRouteTo(),bus.getRoute().getDistance());
			routeRepo.save(route1);
			bus.setRoute(route1);
			return busRepo.save(bus);
		}
		routeRepo.save(route);
		bus.setRoute(route);
		return busRepo.save(bus);
		
	}

	@Override
	public Bus deleteBus(Integer busId, String key) throws BusException, AdminException {
		CurrentAdminSession admin = currAdminRepo.findByaid(key);
		if(admin == null) {
			throw new AdminException("key is not valid! Please provide a valid key.");
		}
		
		Optional<Bus> bus = busRepo.findById(busId);
		
		if(bus.isPresent()) {
			Bus existingBus = bus.get();
			
			if(LocalDate.now().isBefore(existingBus.getBusJourneyDate()) && existingBus.getAvailableSeats()!=existingBus.getSeats()) {
				throw new BusException("Can't delete scheduled and reserved bus.");
			}
			
			busRepo.delete(existingBus);
			return existingBus;
		}
		else 
			throw new BusException("Bus not found with busId: "+busId);
	}

	//admin + user access methods

	@Override
	public List<Bus> viewAllBuses() throws BusException {
		List<Bus> busList = busRepo.findAll();
		if(busList.isEmpty()) {
			throw new BusException("No bus found at this moment. Try again later!");
		}
		return busList;
	}
	
	@Override
	public Bus viewBus(Integer busId) throws BusException {
		Optional<Bus> existingBus = busRepo.findById(busId);
		if(!existingBus.isPresent()) {
			throw new BusException("No bus exist with busId: "+busId);
		}
		return existingBus.get();
		
	}

	@Override
	public List<Bus> viewBusByBusType(String busType) throws BusException {
		List<Bus> busListTypeBus = busRepo.findByBusType(busType);
		if(busListTypeBus.isEmpty()) {
			throw new BusException("There are no buses with bus type: "+busType);
		}
		return busListTypeBus;
	}
	@Override
	public Integer getTotalBusCount() {
		return busRepo.findAll().size();
	}


}
