package com.fdmgroup.movierentalsystem.repository;

import com.fdmgroup.movierentalsystem.model.User;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for User entities.
 * 
 * <p>
 * This interface provides methods to interact with the User entities in the
 * database.
 * </p>
 * 
 * @author Wong Mann Joe
 * @version 1.0
 * @date 2024-04-01
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	/**
	 * Finds a user by their mobile number.
	 * 
	 * @param mobileNumber The mobile number of the user
	 * @return An optional containing the user if found, otherwise empty
	 */
	Optional<User> findByMobileNumber(String mobileNumber);

	/**
	 * Finds a user by their email address.
	 * 
	 * @param emailAddress The email address of the user
	 * @return An optional containing the user if found, otherwise empty
	 */
	Optional<User> findByEmailAddress(String emailAddress);

}