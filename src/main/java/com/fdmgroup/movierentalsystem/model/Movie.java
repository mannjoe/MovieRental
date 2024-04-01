package com.fdmgroup.movierentalsystem.model;

import jakarta.persistence.*;

/**
 * Represents a movie entity.
 * 
 * @author Wong Mann Joe
 * @version 1.0
 * @date 2024-04-01
 */
@Entity
@Table(name = "movies")
public class Movie {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long movieId;

	private String name;
	private String casts;
	private String description;
	private String genres;
	private int duration;
	private int releasedYear;
	private String imageUrl;
	private String videoUrl;

	/**
	 * Default constructor.
	 */
	public Movie() {

	}

	/**
	 * Parameterized constructor to initialize a movie object.
	 * 
	 * @param name         Name of the movie
	 * @param casts        Casts of the movie
	 * @param description  Description of the movie
	 * @param genres       Genres of the movie
	 * @param duration     Duration of the movie (in minutes)
	 * @param releasedYear Year the movie was released
	 * @param imageUrl     URL of the movie's image
	 * @param videoUrl     URL of the movie's video
	 */
	public Movie(String name, String casts, String description, String genres, int duration, int releasedYear,
			String imageUrl, String videoUrl) {
		super();
		setName(name);
		setCasts(casts);
		setDescription(description);
		setGenres(genres);
		setDuration(duration);
		setReleasedYear(releasedYear);
		setImageUrl(imageUrl);
		setVideoUrl(videoUrl);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCasts() {
		return casts;
	}

	public void setCasts(String casts) {
		this.casts = casts;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getGenres() {
		return genres;
	}

	public void setGenres(String genres) {
		this.genres = genres;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public int getReleasedYear() {
		return releasedYear;
	}

	public void setReleasedYear(int releasedYear) {
		this.releasedYear = releasedYear;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getVideoUrl() {
		return videoUrl;
	}

	public void setVideoUrl(String videoUrl) {
		this.videoUrl = videoUrl;
	}
}