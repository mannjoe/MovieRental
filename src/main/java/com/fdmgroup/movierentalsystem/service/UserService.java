package com.fdmgroup.movierentalsystem.service;

import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fdmgroup.movierentalsystem.model.User;
import com.fdmgroup.movierentalsystem.repository.UserRepository;

/**
 * Service class for managing user-related operations.
 * 
 * <p>
 * This class provides methods to authenticate users, register new users, and
 * find users by ID.
 * </p>
 * 
 * @author Wong Mann Joe
 * @version 1.0
 * @date 2024-04-01
 */
@Service
public class UserService {

	private static final Logger logger = LogManager.getLogger(UserService.class);

	private final UserRepository userRepository;

	@Autowired
	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	/**
	 * Authenticates a user based on the provided identifier (mobile number or
	 * email) and password.
	 * 
	 * @param identifier The user's mobile number or email address
	 * @param password   The user's password
	 * @return The user if authentication is successful, or an empty Optional if
	 *         authentication fails
	 */
	public Optional<User> authenticate(String identifier, String password) {
		Optional<User> userByMobile = userRepository.findByMobileNumber(identifier);
		Optional<User> userByEmail = userRepository.findByEmailAddress(identifier);
		Optional<User> user = userByMobile.isPresent() ? userByMobile : userByEmail;
		if (user.isPresent() && password.equals(user.get().getPassword())) {
			logger.info("User authentication successful for user: {}", user.get().getFirstName());
		} else {
			logger.warn("User authentication failed for identifier: {}", identifier);
		}
		return user;
	}

	/**
	 * Registers a new user.
	 * 
	 * @param user The user to be registered
	 * @return true if the user is successfully registered, false if a user with the
	 *         same mobile number or email already exists
	 */
	public boolean registerNewUser(User user) {
		Optional<User> userSameMobile = userRepository.findByMobileNumber(user.getMobileNumber());
		Optional<User> userSameEmail = userRepository.findByEmailAddress(user.getEmailAddress());
		if (userSameMobile.isPresent() || userSameEmail.isPresent()) {
			logger.warn("Registration failed. User with the same mobile number or email already exists");
			return false;
		}
		userRepository.save(user);
		logger.info("New user registered successfully: {}", user.getFirstName());
		return true;
	}

	/**
	 * Finds a user by userId.
	 * 
	 * @param userId The ID of the user to find
	 * @return An Optional containing the user if found, or an empty Optional if not
	 *         found
	 */
	public Optional<User> findUserById(long userId) {
		return userRepository.findById(userId);
	}
}