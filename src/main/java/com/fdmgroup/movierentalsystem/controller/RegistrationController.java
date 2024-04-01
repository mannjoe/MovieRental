package com.fdmgroup.movierentalsystem.controller;

import java.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.fdmgroup.movierentalsystem.model.User;
import com.fdmgroup.movierentalsystem.service.UserService;

import jakarta.servlet.http.HttpSession;

/**
 * Controller for handling user registration.
 * 
 * @author Wong Mann Joe
 * @version 1.0
 * @date 2024-04-01
 */
@Controller
public class RegistrationController {

	@Autowired
	private UserService userService;

	/**
	 * Displays the registration form.
	 * 
	 * @param session HttpSession object to check if the user is already logged in
	 * @return The registration form if the user is not logged in, otherwise
	 *         redirects to the home page
	 */
	@GetMapping("/register")
	public String showRegistrationForm(HttpSession session) {
		if (session.getAttribute("name") != null) {
			return "redirect:/home";
		}
		return "register";
	}

	/**
	 * Processes the registration form submission.
	 * 
	 * @param firstName       First name of the user
	 * @param lastName        Last name of the user
	 * @param dob             Date of birth of the user
	 * @param emailAddress    Email address of the user
	 * @param mobile          Mobile number of the user
	 * @param password        Password chosen by the user
	 * @param confirmPassword Confirmation of the password
	 * @param model           Model object to add attributes for the view
	 * @return Redirects to the login page upon successful registration, or displays
	 *         the registration form again with appropriate error messages
	 */
	@PostMapping("/register")
	public String processRegistrationForm(@RequestParam("first-name") String firstName,
			@RequestParam("last-name") String lastName, @RequestParam("dob") LocalDate dob,
			@RequestParam("email") String emailAddress, @RequestParam("mobile") String mobile,
			@RequestParam("password") String password, @RequestParam("confirm-password") String confirmPassword,
			Model model) {
		if (!isPasswordComplex(password)) {
			model.addAttribute("passwordError",
					"Password must be 8 characters long with at least 1 uppercase, 1 lowercase, 1 digit, and 1 special character.");
			return "register";
		}
		if (!password.equals(confirmPassword)) {
			model.addAttribute("confirmPasswordError", "Passwords do not match.");
			return "register";
		}
		User user = new User(firstName, lastName, dob, password, mobile, emailAddress);
		if (userService.registerNewUser(user)) {
			return "redirect:/login";
		} else {
			model.addAttribute("registrationError",
					"User with the same mobile number or email address already exists.");
			return "register";
		}
	}

	/**
	 * Checks if the password meets complexity requirements.
	 * 
	 * @param password Password to be checked
	 * @return True if the password meets complexity requirements, false otherwise
	 */
	private boolean isPasswordComplex(String password) {
		return password.length() >= 8 && password.matches(".*[A-Z].*") && password.matches(".*[a-z].*")
				&& password.matches(".*\\d.*") && password.matches(".*[!@#$%^&*()].*");
	}
}