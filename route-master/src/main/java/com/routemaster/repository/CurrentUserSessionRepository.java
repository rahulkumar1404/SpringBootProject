package com.routemaster.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.routemaster.model.CurrentUserSession;

@Repository
public interface CurrentUserSessionRepository extends JpaRepository<CurrentUserSession, Integer>{
	public CurrentUserSession findByUuid(String uuid);

}
