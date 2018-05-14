package com.cafe24.lms.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cafe24.lms.domain.Item;
import com.cafe24.lms.service.ItemService;

@Controller
@RequestMapping("/board")
public class ItemController {
	
	@Autowired
	private ItemService service;
	
	
	@ResponseBody
	@RequestMapping({ "", "/main" })
	public Page<Item> index(
			@RequestParam(value = "kwd", required = true, defaultValue = "") String kwd,
			@PageableDefault(size = 10, sort = "no", direction = Sort.Direction.DESC) Pageable pageable) {

		Page<Item> itemPage = service.getPage(pageable, kwd);


		return itemPage;
	}

}
