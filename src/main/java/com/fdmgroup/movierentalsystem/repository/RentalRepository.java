package com.fdmgroup.movierentalsystem.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.fdmgroup.movierentalsystem.model.Movie;
import com.fdmgroup.movierentalsystem.model.Rental;

/**
 * Repository interface for Rental entities.
 * 
 * <p>
 * This interface provides methods to interact with the Rental entities in the
 * database.
 * </p>
 * 
 * @author Wong Mann Joe
 * @version 1.0
 * @date 2024-04-01
 */
@Repository
public interface RentalRepository extends JpaRepository<Rental, Long> {

	/**
	 * Retrieves a list of movies rented by a specific user that have not expired
	 * yet.
	 * 
	 * @param userId The ID of the user
	 * @return A list of movies rented by the user and not expired yet
	 */
	@Query("SELECT r.movie FROM Rental r WHERE r.user.userId = :userId AND r.expiryDate > CURRENT_TIMESTAMP")
	List<Movie> findMoviesRentedByUserId(@Param("userId") long userId);

	/**
	 * Retrieves a rental record by user ID and movie ID.
	 * 
	 * @param userId  The ID of the user
	 * @param movieId The ID of the movie
	 * @return The rental record if found, otherwise null
	 */
	@Query("SELECT r.movie FROM Rental r WHERE r.user.userId = :userId AND r.movie.movieId = :movieId")
	Optional<Rental> findByUserIdAndMovieId(@Param("userId") long userId, @Param("movieId") long movieId);

}