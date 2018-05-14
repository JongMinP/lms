package com.cafe24.lms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cafe24.lms.domain.Item;
import com.cafe24.lms.repository.ItemRepository;

@Service
@Transactional
public class ItemService {
	
	@Autowired
	private ItemRepository itemRepositroy;
	
	
	public void insertItem(Item item) {
		itemRepositroy.save(item);
	}
	
	public List<Item> getList(){
		
		return itemRepositroy.findAll();
	}
	
	public Page<Item> getPage(Pageable pageable , String kwd){
		
		return itemRepositroy.findAllByPage(pageable,kwd);
	}
	
	public Item getItem(Long no) {
		
		return itemRepositroy.findOne(no);
	}
	
	public Page<Item> getRentPage(Pageable pageable){
		
		return itemRepositroy.findAllRentPage(pageable);
	}
	

}
