package com.fdmgroup.movierentalsystem.service;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fdmgroup.movierentalsystem.model.User;
import com.fdmgroup.movierentalsystem.repository.UserRepository;

/**
 * Service class for managing user-related operations.
 * 
 * @author Wong Mann Joe
 * @version 1.0
 * @date 2024-04-01
 */
@Service
public class UserService {

	private static final Logger logger = LoggerFactory.getLogger(UserService.class);

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
	 * @return The first name of the authenticated user, or an empty string if
	 *         authentication fails
	 */
	public String authenticate(String identifier, String password) {
		logger.debug("Authenticating user with identifier: {}", identifier);
		Optional<User> userByMobile = userRepository.findByMobileNumber(identifier);
		Optional<User> userByEmail = userRepository.findByEmailAddress(identifier);
		Optional<User> userOptional = userByMobile.isPresent() ? userByMobile : userByEmail;
		if (userOptional.isPresent()) {
			User user = userOptional.get();
			if (password.equals(user.getPassword())) {
				logger.info("User authentication successful for user: {}", user.getFirstName());
				return user.getFirstName();
			}
		}
		logger.warn("User authentication failed for identifier: {}", identifier);
		return "";
	}

	/**
	 * Registers a new user.
	 * 
	 * @param user The user to be registered
	 * @return true if the user is successfully registered, false if a user with the
	 *         same mobile number or email already exists
	 */
	public boolean registerNewUser(User user) {
		logger.debug("Registering new user: {}", user);
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
}