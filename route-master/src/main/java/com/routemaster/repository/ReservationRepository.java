package com.routemaster.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.routemaster.model.Reservation;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Integer>{

}
