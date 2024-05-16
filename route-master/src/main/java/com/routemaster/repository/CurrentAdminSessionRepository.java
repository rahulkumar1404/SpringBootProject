package com.routemaster.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.routemaster.model.CurrentAdminSession;

@Repository
public interface CurrentAdminSessionRepository extends JpaRepository<CurrentAdminSession, Integer> {
	@Query("select c from CurrentAdminSession c where c.aid=?1")
	public CurrentAdminSession findByaid(String aid);
}
