package com.fdmgroup.movierentalsystem.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fdmgroup.movierentalsystem.model.Movie;
import com.fdmgroup.movierentalsystem.repository.MovieRepository;
import com.fdmgroup.movierentalsystem.repository.RentalRepository;

/**
 * Service class for managing movie-related operations.
 * 
 * <p>
 * This class provides methods to interact with movies in the system.
 * </p>
 * 
 * @author Wong Mann Joe
 * @version 1.0
 * @date 2024-04-01
 */
@Service
public class MovieService {

	private static final Logger logger = LogManager.getLogger(MovieService.class);

	private final MovieRepository movieRepository;
	private final RentalRepository rentalRepository;

	@Autowired
	public MovieService(MovieRepository movieRepository, RentalRepository rentalRepository) {
		this.movieRepository = movieRepository;
		this.rentalRepository = rentalRepository;
	}

	/**
	 * Retrieves all movies.
	 * 
	 * @return A list of all movies
	 */
	public List<Movie> getAllMovies() {
		logger.debug("Retrieving all movies");
		return movieRepository.findAll();
	}

	/**
	 * Finds a movie by its ID.
	 * 
	 * @param movieId The ID of the movie
	 * @return An optional containing the movie if found, otherwise empty
	 */
	public Optional<Movie> findMovieById(long movieId) {
		return movieRepository.findById(movieId);
	}

	/**
	 * Finds a movie by its name and released year.
	 * 
	 * @param name         The name of the movie
	 * @param releasedYear The year the movie was released
	 * @return The movie if found, otherwise null
	 */
	public Movie findMovieByNameAndReleasedYear(String name, int releasedYear) {
		logger.debug("Finding movie by name '{}' and released year '{}'", name, releasedYear);
		return movieRepository.findByNameAndReleaseYear(name, releasedYear);
	}

	/**
	 * Saves or updates a movie.
	 * 
	 * @param movie The movie to be saved or updated
	 * @return The saved or updated movie
	 */
	public Movie saveOrUpdateMovie(Movie movie) {
		logger.debug("Saving or updating movie: {}", movie);
		return movieRepository.save(movie);
	}

	/**
	 * Finds unrented movies for a given user.
	 * 
	 * @param userId The ID of the user
	 * @return A list of unrented movies
	 */
	public List<Movie> findUnrentedMovies(long userId) {
		List<Movie> rentedMovies = rentalRepository.findMoviesRentedByUserId(userId);
		List<Movie> allMovies = movieRepository.findAll();
		List<Movie> unrentedMovies = new ArrayList<>();

		for (Movie movie : allMovies) {
			if (!rentedMovies.contains(movie)) {
				unrentedMovies.add(movie);
			}
		}
		return unrentedMovies;
	}
}