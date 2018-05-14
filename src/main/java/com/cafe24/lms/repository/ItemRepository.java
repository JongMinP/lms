package com.cafe24.lms.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.cafe24.lms.domain.Item;

public interface ItemRepository extends JpaRepository<Item, Long>{
	
	@Query("SELECT i FROM Item i WHERE i.title LIKE concat('%',:kwd,'%') ORDER BY i.no desc ")
	Page<Item> findAllByPage(Pageable pageable,@Param("kwd") String kwd);
	
	@Query("SELECT i FROM Item i WHERE i.status = 'NOT' ORDER BY i.no desc ")
	Page<Item> findAllRentPage(Pageable pageable);
	
	
//	@Modifying
//	@Query(value = "UPDATE Item i SET i.status = :#{#item.status} WHERE i.no = :#{#item.no}")
//	int update(@Param("item") Item item);
	
}
