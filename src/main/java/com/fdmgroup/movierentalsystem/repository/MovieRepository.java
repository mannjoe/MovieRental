package com.fdmgroup.movierentalsystem.repository;

import com.fdmgroup.movierentalsystem.model.Movie;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for Movie entities.
 * 
 * @author Wong Mann Joe
 * @version 1.0
 * @date 2024-04-01
 */
@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {

	/**
	 * Finds a movie by its name and released year.
	 * 
	 * @param name         The name of the movie
	 * @param releasedYear The released year of the movie
	 * @return The movie object if found, otherwise null
	 */
	Movie findByNameAndReleasedYear(String name, int releasedYear);

}