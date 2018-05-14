package com.cafe24.lms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.cafe24.lms.domain.Rent;

public interface RentRepository extends JpaRepository<Rent, Long>{
	
	@Query("SELECT r FROM Rent r WHERE r.item.no = :no")
	Rent findItemNo(@Param("no") Long no);
	

}
