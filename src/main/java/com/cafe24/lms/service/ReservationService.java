package com.cafe24.lms.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cafe24.lms.domain.Item;
import com.cafe24.lms.domain.Rent;
import com.cafe24.lms.domain.Reservation;
import com.cafe24.lms.domain.Status;
import com.cafe24.lms.domain.User;
import com.cafe24.lms.repository.ItemRepository;
import com.cafe24.lms.repository.RentRepository;
import com.cafe24.lms.repository.ReservationRepository;
import com.cafe24.lms.repository.UserRepository;

@Service
@Transactional
public class ReservationService {

	@Autowired
	private ReservationRepository reservationRepositroy;

	@Autowired
	private ItemRepository itemRepository;

	@Autowired
	private UserRepository userRepositroy;

	@Autowired
	private RentRepository rentRepositroy;

	public Page<Reservation> getReservationPage(Pageable pageable) {

		return reservationRepositroy.findAll(pageable);
	}

	public Reservation makeReservation(Long no, Long userNo) {

		Item item = itemRepository.findOne(no);
		User user = userRepositroy.findOne(userNo);
		Rent rent = rentRepositroy.findItemNo(no);

		if (Status.ABLE.equals(item.getStatus())) {
			return null;
		}

		int orderNo = reservationRepositroy.findOrderNo(item.getNo()) + 1;
		Reservation reservation = new Reservation();
		reservation.setRent(rent);
		reservation.setUser(user);
		reservation.setOrderNo(orderNo);

		reservationRepositroy.save(reservation);

		return reservation;

	}

}
