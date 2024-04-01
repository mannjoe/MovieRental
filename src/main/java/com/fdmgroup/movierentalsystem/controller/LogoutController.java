package com.fdmgroup.movierentalsystem.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * This controller handles logout-related requests.
 *
 * @author Wong Mann Joe
 * @version 1.0
 * @date 2024-04-01
 */
@Controller
public class LogoutController {

	/**
	 * Logs out the user by invalidating the session and redirects to the login
	 * page.
	 *
	 * @param session  HttpSession object to manage the session
	 * @param request  HttpServletRequest object to obtain the session
	 * @param response HttpServletResponse object to send the redirect response
	 * @return Redirects to the login page
	 */
	@GetMapping("/logout")
	public String logout(HttpSession session, HttpServletRequest request, HttpServletResponse response) {
		session.invalidate();
		return "redirect:/login";
	}
}