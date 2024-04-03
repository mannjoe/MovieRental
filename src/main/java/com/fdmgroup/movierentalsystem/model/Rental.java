package com.fdmgroup.movierentalsystem.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity
@Table(name = "rentals")
public class Rental {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long rentalId;

	@ManyToOne
	@JoinColumn(name = "userId")
	private User user;

	@ManyToOne
	@JoinColumn(name = "movieId")
	private Movie movie;

	private double amountPaid;
	private LocalDateTime dateRented;
	private LocalDateTime expiryDate;

	/**
	 * Default constructor.
	 */
	public Rental() {

	}

	/**
	 * Parameterized constructor to initialize a rental object.
	 * 
	 * @param user       The user who rented the movie
	 * @param movie      The movie rented
	 * @param amountPaid The amount paid for the rental
	 * @param days       Number of days for rental
	 */
	public Rental(User user, Movie movie, double amountPaid, int days) {
		setUser(user);
		setMovie(movie);
		setAmountPaid(amountPaid);
		setDateRented();
		setExpiryDate(days);
	}

	public long getRentalId() {
		return rentalId;
	}

	public void setRentalId(long rentalId) {
		this.rentalId = rentalId;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Movie getMovie() {
		return movie;
	}

	public void setMovie(Movie movie) {
		this.movie = movie;
	}

	public double getAmountPaid() {
		return amountPaid;
	}

	public void setAmountPaid(double amountPaid) {
		this.amountPaid = amountPaid;
	}

	public LocalDateTime getDateRented() {
		return dateRented;
	}

	public void setDateRented() {
		this.dateRented = LocalDateTime.now();
	}

	public LocalDateTime getExpiryDate() {
		return expiryDate;
	}

	/**
	 * Sets the expiry date of the rental based on the number of days.
	 * 
	 * @param days Number of days for rental
	 */
	public void setExpiryDate(int days) {
		this.expiryDate = this.dateRented.plusDays(days);
	}

	/**
	 * Gets the formatted expiry date of the rental.
	 * 
	 * @return The formatted expiry date string
	 */
	public String getFormattedExpiryDate() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		return expiryDate.format(formatter);
	}
}