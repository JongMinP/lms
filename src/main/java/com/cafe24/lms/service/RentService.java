package com.cafe24.lms.service;

import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cafe24.lms.domain.Item;
import com.cafe24.lms.domain.Rent;
import com.cafe24.lms.domain.Status;
import com.cafe24.lms.domain.User;
import com.cafe24.lms.repository.ItemRepository;
import com.cafe24.lms.repository.RentRepository;
import com.cafe24.lms.repository.UserRepository;

@Service
@Transactional
public class RentService {

	@Autowired
	private ItemRepository itemRepositroy;

	@Autowired
	private RentRepository rentRepository;

	@Autowired
	private UserRepository userRepository;

	public Page<Rent> getRentPage(Pageable pageable) {

		return rentRepository.findAll(pageable);
	}

	public Rent rentItem(Long no, Long userNo) {

		Item item = itemRepositroy.findOne(no);
		User user = userRepository.findOne(userNo);

		if (Status.NOT.equals(item.getStatus())) {
			return null;
		}

		Rent rent = new Rent();
		Date rentDate = new Date();

		item.setStatus(Status.NOT);

		rent.setItem(item);
		rent.setRentDate(rentDate);
		rent.setReturnDate(returnDate(rentDate));
		rent.setUser(user);
		rentRepository.save(rent);

		return rent;

	}

	public Date returnDate(Date renDate) {

		Calendar cal = Calendar.getInstance();
		cal.setTime(renDate);
		cal.add(Calendar.DATE, 7);

		return cal.getTime();
	}

}
