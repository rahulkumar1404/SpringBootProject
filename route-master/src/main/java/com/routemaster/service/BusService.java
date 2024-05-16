package com.routemaster.service;

import java.util.List;

import com.routemaster.exception.AdminException;
import com.routemaster.exception.BusException;
import com.routemaster.model.Bus;

public interface BusService {
	//admin methods
	public Bus addBus(Bus bus,String key) throws BusException,AdminException;
	public Bus updateBus(Bus bus, String key) throws BusException , AdminException;
	public Bus deleteBus(Integer busId,String key) throws BusException , AdminException;
	public Integer getTotalBusCount();
	
	//admin + user methods
	public Bus viewBus(Integer busId) throws BusException;
	public List<Bus> viewBusByBusType(String busType) throws BusException;
	public List<Bus> viewAllBuses() throws BusException;

}
