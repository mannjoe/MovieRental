package com.fdmgroup.movierentalsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fdmgroup.movierentalsystem.service.UserService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

/**
 * This controller handles login-related requests.
 *
 * @author Wong Mann Joe
 * @version 1.0
 * @date 2024-04-01
 */
@Controller
public class LoginController {

	@Autowired
	private UserService userService;

	/**
	 * Displays the login page.
	 *
	 * @param session HttpSession object to check for an existing session
	 * @return The login page if no session exists, otherwise redirects to the home
	 *         page
	 */
	@GetMapping(value = { "/", "/login" })
	public String showLoginPage(HttpSession session) {
		if (session.getAttribute("name") != null) {
			return "redirect:/home";
		}
		return "login";
	}

	/**
	 * Processes the login request.
	 *
	 * @param identifier User identifier (e.g., email or username)
	 * @param password   User password
	 * @param model      Model object to add attributes for rendering the view
	 * @param session    HttpSession object to manage the session
	 * @param request    HttpServletRequest object to obtain the session
	 * @return Redirects to the home page if login is successful, otherwise returns
	 *         to the login page with an error message
	 */
	@PostMapping("/login")
	public String processLogin(@RequestParam String identifier, @RequestParam String password, Model model,
			HttpSession session, HttpServletRequest request) {
		String name = userService.authenticate(identifier, password);
		if (!name.isBlank()) {
			session = request.getSession(true);
			session.setAttribute("name", name);
			session.setMaxInactiveInterval(900);
			return "redirect:/home";
		} else {
			model.addAttribute("error", "Invalid login identifier or password");
			return "login";
		}
	}
}