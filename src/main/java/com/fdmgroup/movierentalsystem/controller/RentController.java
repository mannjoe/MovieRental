package com.fdmgroup.movierentalsystem.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fdmgroup.movierentalsystem.model.Movie;
import com.fdmgroup.movierentalsystem.model.Rental;
import com.fdmgroup.movierentalsystem.model.User;
import com.fdmgroup.movierentalsystem.service.MovieService;
import com.fdmgroup.movierentalsystem.service.RentalService;
import com.fdmgroup.movierentalsystem.service.UserService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class RentController {

	@Autowired
	private MovieService movieService;

	@Autowired
	private UserService userService;

	@Autowired
	private RentalService rentalService;

	@Value("${rental.price.per.day}")
	private double rentalPricePerDay;

	/**
	 * Handles GET requests for the rental page.
	 * 
	 * @param movieId The ID of the movie to be displayed on the rental page.
	 * @param model   The model to be populated with movie data.
	 * @param request The HTTP servlet request to retrieve the session.
	 * @return The view name for the rental page or a redirection to the login page
	 *         if the user is not logged in or if the session is not valid.
	 */
	@GetMapping("/rent/{movieId}")
	public String showRentalPage(@PathVariable long movieId, Model model, HttpServletRequest request) {
		// Retrieve the session
		HttpSession session = request.getSession(false);
		// Check if session exists and if user is logged in
		if (session != null && session.getAttribute("name") != null) {
			// Retrieve the movie by its ID
			Optional<Movie> optionalMovie = movieService.findMovieById(movieId);
			// Check if the movie exists
			if (optionalMovie.isPresent()) {
				Movie movie = optionalMovie.get();
				// Add the movie object to the model
				model.addAttribute("movie", movie);
				model.addAttribute("rentalPricePerDay", rentalPricePerDay);
				// Return the view name for the rental page
				return "rent";
			} else {
				// If movie does not exist, redirect to error page
				return "error";
			}
		} else {
			// If session is not valid or user is not logged in, redirect to login page
			return "redirect:/login";
		}
	}

	@PostMapping("/rent-confirm")
	public String showRentConfirmPage(@RequestParam long movieId, @RequestParam int rentalDays,
			@RequestParam double totalPrice, HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		if (session != null && session.getAttribute("name") != null) {
			User user = userService.findUserById((Long) session.getAttribute("userId")).get();
			Movie movie = movieService.findMovieById(movieId).get();
			Rental rental = new Rental(user, movie, totalPrice, rentalDays);
			rentalService.saveRental(rental);
			return "rent-confirm";
		} else {
			return "redirect:/login";
		}

	}
}