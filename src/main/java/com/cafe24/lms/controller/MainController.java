package com.cafe24.lms.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.cafe24.lms.domain.Item;
import com.cafe24.lms.domain.Rent;
import com.cafe24.lms.domain.Reservation;
import com.cafe24.lms.domain.User;
import com.cafe24.lms.service.ItemService;
import com.cafe24.lms.service.RentService;
import com.cafe24.lms.service.ReservationService;
import com.cafe24.pager.Pager;
import com.cafe24.security.Auth;
import com.cafe24.security.AuthUser;

@Controller
public class MainController {

	@Autowired
	private ItemService service;

	@Autowired
	private RentService rentService;

	@Autowired
	private ReservationService reservationService;

	private static final Log log = LogFactory.getLog(MainController.class);

	@RequestMapping({ "", "/main" })
	public String index(Model model, @RequestParam(value = "kwd", required = true, defaultValue = "") String kwd,
			@PageableDefault(size = 10, sort = "no", direction = Sort.Direction.DESC) Pageable pageable) {

		Page<Item> itemPage = service.getPage(pageable, kwd);

		Pager pager = new Pager();
		pager.pagination((int) itemPage.getNumber() + 1, (int) itemPage.getTotalElements());

		model.addAttribute("itemPage", itemPage);
		model.addAttribute("pager", pager);
		model.addAttribute("kwd", kwd);

		return "main/index";
	}

	@Auth
	@RequestMapping("/rent")
	public String rent(
			@RequestParam(value = "no", required = true, defaultValue = "0") Long no,
			@AuthUser User authUser,
			RedirectAttributes rttr) {

		Rent rent = rentService.rentItem(no, authUser.getNo());

		if (rent == null) {
			rttr.addFlashAttribute("msg","rent");
			return "redirect:main";
		}

		return "main/rent";
	}

	@Auth
	@RequestMapping("/reservation")
	public String reservation(
			@RequestParam(value = "no", required = true, defaultValue = "0") Long no,
			@AuthUser User authUser,
			RedirectAttributes rttr) {

		Reservation reservation = reservationService.makeReservation(no, authUser.getNo());

		if (reservation == null) {
			rttr.addFlashAttribute("msg","reservation");
			return "redirect:main";
		}

		return "main/rent";
	}

}
