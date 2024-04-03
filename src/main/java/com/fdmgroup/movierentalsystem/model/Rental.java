package com.fdmgroup.movierentalsystem.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "rentals")
public class Rental {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long rentalId;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

	@ManyToOne
	@JoinColumn(name = "movie_id")
	private Movie movie;

	private double amountPaid;
	private LocalDateTime dateRented;
	private LocalDateTime expiryDate;

	// Constructors, getters, and setters
	// Constructor
	public Rental() {

	}

	public Rental(User user, Movie movie, double amountPaid, int days) {
		super();
		setUser(user);
		setMovie(movie);
		setAmountPaid(amountPaid);
		setDateRented();
		setExpiryDate(days);
	}

	// Getters and Setters
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

	public void setExpiryDate(int days) {
		this.expiryDate = this.dateRented.plusDays(days);
	}
}