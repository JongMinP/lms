package com.cafe24.lms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cafe24.lms.domain.Item;
import com.cafe24.lms.domain.Rent;
import com.cafe24.lms.domain.Reservation;
import com.cafe24.lms.repository.ItemRepository;
import com.cafe24.lms.service.ItemService;
import com.cafe24.lms.service.RentService;
import com.cafe24.lms.service.ReservationService;
import com.cafe24.pager.Pager;
import com.cafe24.security.Auth;
import com.cafe24.security.Auth.Role;

@Auth(role = Role.ADMIN)
@Controller
@RequestMapping( "/admin" )
public class AdminController {
	
	
	@Autowired
	private RentService rentService;
	
	@Autowired
	private ReservationService reservationService;
	
	@RequestMapping( { "", "/rent", "/main" } )
	public String main( 
			Model model,
			@PageableDefault(size = 10 ,sort = "no", direction = Sort.Direction.DESC )
			Pageable pageable) {
		
		Page<Rent> rentPage = rentService.getRentPage(pageable);
		
		Pager pager = new Pager();
		pager.pagination((int) rentPage.getNumber() + 1, (int) rentPage.getTotalElements());
		
		model.addAttribute("rentPage",rentPage);
		model.addAttribute("pager", pager);
		return "admin/rent";
	}
	
	@RequestMapping( "/reserve" )
	public String board(Model model,
			@PageableDefault(size = 10,sort= "no",direction=Sort.Direction.DESC)
			Pageable pageable) {
		
		
		Page<Reservation> reservations = reservationService.getReservationPage(pageable);
		Pager pager = new Pager();
		pager.pagination((int) reservations.getNumber() + 1, (int) reservations.getTotalElements());
		
		
		model.addAttribute("pages",reservations);
		model.addAttribute("pager", pager);
		
		return "admin/reserve";
	}
	
}
