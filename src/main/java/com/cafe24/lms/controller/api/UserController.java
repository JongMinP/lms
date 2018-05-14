package com.cafe24.lms.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cafe24.lms.dto.JSONResult;
import com.cafe24.lms.service.UserService;

@Controller("userAPIController")
@RequestMapping("/api/user")
public class UserController {

	@Autowired
	private UserService	 userService;

	@ResponseBody
	@RequestMapping("/checkemail")
	public JSONResult checkMail(
			@RequestParam(value = "email", required = true, defaultValue = "") String email) {
		
		boolean result = userService.existEmail(email);
		
		return JSONResult.success(result == false ? "not exists" : "exist");
	}

}
