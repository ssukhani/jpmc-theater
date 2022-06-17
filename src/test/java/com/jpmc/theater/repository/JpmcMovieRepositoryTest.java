package com.jpmc.theater.repository;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Optional;

import org.junit.jupiter.api.Test;

import com.jpmc.theater.model.Movie;

public class JpmcMovieRepositoryTest {

	RecreationEventRepository<Movie> movieRepository =  new JpmcMovieRepository();

	@Test
	void testGetAllRecreationEvent() {
        assertNotNull(movieRepository.getAllRecreationEvent());
        assertNotEquals(0,  movieRepository.getAllRecreationEvent().size());
	}

	@Test
	void testGetRecreationEvent() {		
		String movieTitle = "Turning Red";
		Optional<Movie> movieTurningRed = movieRepository.getRecreationEvent(movieTitle);
		assertTrue(movieTurningRed.isPresent());
		assertEquals(movieTitle, movieTurningRed.get().getTitle());
	}
}
