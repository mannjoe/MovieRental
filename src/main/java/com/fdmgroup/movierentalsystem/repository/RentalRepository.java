package com.fdmgroup.movierentalsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fdmgroup.movierentalsystem.model.Rental;

@Repository
public interface RentalRepository extends JpaRepository<Rental, Long> {

}