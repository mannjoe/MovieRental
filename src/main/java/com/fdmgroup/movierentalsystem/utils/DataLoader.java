package com.fdmgroup.movierentalsystem.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fdmgroup.movierentalsystem.model.Movie;
import com.fdmgroup.movierentalsystem.service.MovieService;

import jakarta.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * Utility class for loading data from a JSON file into the database on
 * application startup.
 * 
 * @author Wong Mann Joe
 * @version 1.0
 * @date 2024-04-01
 */
@Component
public class DataLoader {

	private final MovieService movieService;
	private final ResourceLoader resourceLoader;

	@Autowired
	public DataLoader(MovieService movieService, ResourceLoader resourceLoader) {
		this.movieService = movieService;
		this.resourceLoader = resourceLoader;
	}

	/**
	 * Loads movie data from a JSON file and saves it into the database.
	 * 
	 * @param filePath The path to the JSON file containing movie data
	 */
	public void loadMoviesFromFile(String filePath) {
		try {
			Resource resource = resourceLoader.getResource("classpath:" + filePath);
			InputStream inputStream = resource.getInputStream();
			ObjectMapper objectMapper = new ObjectMapper();
			List<Movie> movies = objectMapper.readValue(inputStream,
					objectMapper.getTypeFactory().constructCollectionType(List.class, Movie.class));

			for (Movie movie : movies) {
				Movie existingMovie = movieService.findMovieByNameAndReleasedYear(movie.getName(),
						movie.getReleaseYear());
				if (existingMovie != null) {
					existingMovie.setGenres(movie.getGenres());
					existingMovie.setDescription(movie.getDescription());
					existingMovie.setDuration(movie.getDuration());
					existingMovie.setCasts(movie.getCasts());
					existingMovie.setImageUrl(movie.getImageUrl());
					existingMovie.setVideoUrl(movie.getVideoUrl());
					movieService.saveOrUpdateMovie(existingMovie);
				} else {
					movieService.saveOrUpdateMovie(movie);
				}
			}
			inputStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Loads data from a JSON file containing movie information on application
	 * startup.
	 */
	@PostConstruct
	public void loadDataOnStartup() {
		loadMoviesFromFile("data/movies.json");
	}
}