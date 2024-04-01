package com.fdmgroup.movierentalsystem.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fdmgroup.movierentalsystem.model.Movie;
import com.fdmgroup.movierentalsystem.repository.MovieRepository;

/**
 * Service class for managing movie-related operations.
 * 
 * @author Wong Mann Joe
 * @version 1.0
 * @date 2024-04-01
 */
@Service
public class MovieService {

	private final MovieRepository movieRepository;

	@Autowired
	public MovieService(MovieRepository movieRepository) {
		this.movieRepository = movieRepository;
	}

	/**
	 * Retrieves all movies.
	 * 
	 * @return A list of all movies
	 */
	public List<Movie> getAllMovies() {
		return movieRepository.findAll();
	}

	/**
	 * Finds a movie by its name and released year.
	 * 
	 * @param name         The name of the movie
	 * @param releasedYear The year the movie was released
	 * @return The movie if found, otherwise null
	 */
	public Movie findMovieByNameAndReleasedYear(String name, int releasedYear) {
		return movieRepository.findByNameAndReleasedYear(name, releasedYear);
	}

	/**
	 * Saves or updates a movie.
	 * 
	 * @param movie The movie to be saved or updated
	 * @return The saved or updated movie
	 */
	public Movie saveOrUpdateMovie(Movie movie) {
		return movieRepository.save(movie);
	}
}