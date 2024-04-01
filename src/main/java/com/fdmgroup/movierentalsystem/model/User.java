package com.fdmgroup.movierentalsystem.model;

import java.time.LocalDate;

import jakarta.persistence.*;

/**
 * Represents a user entity.
 * 
 * @author Wong Mann Joe
 * @version 1.0
 * @date 2024-04-01
 */
@Entity
@Table(name = "users")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long userId;

	private String firstName;
	private String lastName;
	private LocalDate dob;
	private String password;

	@Column(unique = true)
	private String mobileNumber;

	@Column(unique = true)
	private String emailAddress;

	/**
	 * Default constructor.
	 */
	public User() {

	}

	/**
	 * Parameterized constructor to initialize a user object.
	 * 
	 * @param firstName    First name of the user
	 * @param lastName     Last name of the user
	 * @param dob          Date of birth of the user
	 * @param password     Password of the user
	 * @param mobileNumber Mobile number of the user
	 * @param emailAddress Email address of the user
	 */
	public User(String firstName, String lastName, LocalDate dob, String password, String mobileNumber,
			String emailAddress) {
		super();
		setFirstName(firstName);
		setLastName(lastName);
		setDob(dob);
		setPassword(password);
		setMobileNumber(mobileNumber);
		setEmailAddress(emailAddress);
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public LocalDate getDob() {
		return dob;
	}

	public void setDob(LocalDate dob) {
		this.dob = dob;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}
}