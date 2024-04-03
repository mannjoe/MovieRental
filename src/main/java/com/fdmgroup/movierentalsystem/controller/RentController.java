package com.fdmgroup.movierentalsystem.controller;

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
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 * Controller class for handling movie rental operations.
 *
 * @author Wong Mann Joe
 * @version 1.0
 * @date 2024-04-01
 */
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
	 * Displays the rental page for a specific movie.
	 *
	 * @param movieId  The ID of the movie to rent
	 * @param model    The model to be populated with movie data
	 * @param request  The HTTP servlet request
	 * @param response The HTTP servlet response
	 * @return The view name for the rental page
	 */
	@GetMapping("/rent/{movieId}")
	public String showRentalPage(@PathVariable long movieId, Model model, HttpServletRequest request,
			HttpServletResponse response) {
		response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
		response.setHeader("Pragma", "no-cache");
		response.setHeader("Expires", "0");
		HttpSession session = request.getSession(false);
		if (session != null && session.getAttribute("name") != null) {
			Optional<Movie> optionalMovie = movieService.findMovieById(movieId);
			if (optionalMovie.isPresent()) {
				Movie movie = optionalMovie.get();
				boolean alreadyRented = rentalService.isUserAlreadyRentedMovie((Long) session.getAttribute("userId"),
						movieId);
				model.addAttribute("alreadyRented", alreadyRented);
				model.addAttribute("movie", movie);
				model.addAttribute("rentalPricePerDay", rentalPricePerDay);
				if (alreadyRented) {
					return "redirect:/home";
				}
				return "rent";
			} else {
				return "error";
			}
		} else {
			return "redirect:/login";
		}
	}

	/**
	 * Processes the rental transaction.
	 *
	 * @param movieId    The ID of the movie to rent
	 * @param rentalDays The number of rental days
	 * @param totalPrice The total price for the rental
	 * @param request    The HTTP servlet request
	 * @return The view name for the rental success page or redirects to the login
	 *         page
	 */
	@PostMapping("/rent-transaction")
	public String showProcessRent(@RequestParam long movieId, @RequestParam int rentalDays,
			@RequestParam double totalPrice, HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		if (session != null && session.getAttribute("name") != null) {
			User user = userService.findUserById((Long) session.getAttribute("userId")).get();
			Movie movie = movieService.findMovieById(movieId).get();
			Rental rental = new Rental(user, movie, totalPrice, rentalDays);
			rentalService.saveRental(rental);
			session.setAttribute("movieName", movie.getName());
			session.setAttribute("expiryDate", rental.getFormattedExpiryDate());
			return "redirect:/rent-success";
		} else {
			return "redirect:/login";
		}

	}

	/**
	 * Displays the rental success page.
	 *
	 * @param session The HTTP session
	 * @return The view name for the rental success page or redirects to the login
	 *         page
	 */
	@GetMapping("/rent-success")
	public String showRentSuccess(HttpSession session) {
		if (session.getAttribute("name") != null) {
			return "rent-success";
		}
		return "redirect:/login";
	}
}