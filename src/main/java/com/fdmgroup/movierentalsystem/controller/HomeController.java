package com.fdmgroup.movierentalsystem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.fdmgroup.movierentalsystem.model.Movie;
import com.fdmgroup.movierentalsystem.service.MovieService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

/**
 * Controller class for handling requests related to the home page.
 *
 * @author Wong Mann Joe
 * @version 1.0
 * @date 2024-04-01
 */
@Controller
public class HomeController {

	@Autowired
	private MovieService movieService;

	/**
	 * Handles GET requests for the home page.
	 * 
	 * @param model   The model to be populated with movie data.
	 * @param request The HTTP servlet request to retrieve the session.
	 * @return The view name for the home page or a redirection to the login page if
	 *         the user is not logged in.
	 */
	@GetMapping("/home")
	public String getAllMovies(Model model, HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		if (session != null && session.getAttribute("name") != null) {
			List<Movie> movies = movieService.getAllMovies();
			model.addAttribute("movies", movies);
			return "home";
		} else {
			return "redirect:/login";
		}
	}
}