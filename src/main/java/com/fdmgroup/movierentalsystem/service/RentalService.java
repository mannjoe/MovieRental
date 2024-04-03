package com.fdmgroup.movierentalsystem.service;

//RentalService.java

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fdmgroup.movierentalsystem.model.Rental;
import com.fdmgroup.movierentalsystem.repository.RentalRepository;

@Service
public class RentalService {

	private final RentalRepository rentalRepository;

	@Autowired
	public RentalService(RentalRepository rentalRepository) {
		this.rentalRepository = rentalRepository;
	}

	public Rental saveRental(Rental rental) {
		return rentalRepository.save(rental);
	}
}