package com.cafe24.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.cafe24.lms.domain.User;
import com.cafe24.lms.service.UserService;

public class AuthLoginInterceptor extends HandlerInterceptorAdapter {

	// @Autowired
	// private UserService userService;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		String email = request.getParameter("email");
		String password = request.getParameter("password");

		User user = new User();
		user.setEmail(email);
		user.setPassword(password);

		// 어디서든 컨테이너에 접근 가능
		ApplicationContext ac = WebApplicationContextUtils.getWebApplicationContext(request.getServletContext());

		UserService userService = ac.getBean(UserService.class);

		User authUser = userService.getUserEmailandPassword(user);

		if (authUser == null) {
			response.sendRedirect(request.getContextPath() + "/user/login");
			return false;
		}

		HttpSession session = request.getSession(true);
		session.setAttribute("authUser", authUser);

		response.sendRedirect(request.getContextPath());
		return false;
	}

}
