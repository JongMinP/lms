package com.cafe24.lms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.cafe24.lms.domain.Reservation;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {

	@Query("SELECT coalesce(max(r.orderNo), 0) FROM Reservation r join r.rent t WHERE t.item.no = :no")
	Integer findOrderNo(@Param("no") Long itemNo);

}
