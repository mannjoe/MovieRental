package com.fdmgroup.movierentalsystem.service;

import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fdmgroup.movierentalsystem.model.Movie;
import com.fdmgroup.movierentalsystem.model.Rental;
import com.fdmgroup.movierentalsystem.repository.RentalRepository;

/**
 * Service class for managing rental-related operations.
 * 
 * <p>
 * This class provides methods to interact with rentals in the system.
 * </p>
 * 
 * @author Wong Mann Joe
 * @version 1.0
 * @date 2024-04-01
 */
@Service
public class RentalService {

	private static final Logger logger = LogManager.getLogger(RentalService.class);

	private final RentalRepository rentalRepository;

	@Autowired
	public RentalService(RentalRepository rentalRepository) {
		this.rentalRepository = rentalRepository;
	}

	/**
	 * Saves a rental.
	 * 
	 * @param rental The rental to be saved
	 * @return The saved rental
	 */
	public Rental saveRental(Rental rental) {
		logger.debug("Saving rental: {}", rental);
		return rentalRepository.save(rental);
	}

	/**
	 * Finds movies rented by a specific user.
	 * 
	 * @param userId The ID of the user
	 * @return A list of movies rented by the user
	 */
	public List<Movie> findMoviesRentedByUserId(long userId) {
		logger.debug("Finding movies rented by user with ID: {}", userId);
		return rentalRepository.findMoviesRentedByUserId(userId);
	}

	/**
	 * Checks if a user has already rented a specific movie.
	 * 
	 * @param userId  The ID of the user
	 * @param movieId The ID of the movie
	 * @return True if the user has already rented the movie, otherwise false
	 */
	public boolean isUserAlreadyRentedMovie(long userId, long movieId) {
		logger.debug("Checking if user with ID {} has already rented movie with ID {}", userId, movieId);
		Optional<Rental> rental = rentalRepository.findByUserIdAndMovieId(userId, movieId);
		return rental.isPresent();
	}
}