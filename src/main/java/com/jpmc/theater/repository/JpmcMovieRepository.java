package com.jpmc.theater.repository;

import com.jpmc.theater.model.Genre;
import com.jpmc.theater.model.Money;
import com.jpmc.theater.model.Movie;

import java.math.BigDecimal;
import java.math.MathContext;
import java.time.Duration;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.stereotype.Repository;

@Repository
public class JpmcMovieRepository implements RecreationEventRepository<Movie> {

    private final Set<Movie> movies;

    public JpmcMovieRepository(){
    	movies = Stream.of(
        		new Movie("Spider-Man: No Way Home", Genre.SCIFI, Duration.ofMinutes(90), new Money(new BigDecimal("12.50", MathContext.DECIMAL32)), 1),
                new Movie("Turning Red", Genre.FAMILY, Duration.ofMinutes(85), new Money(new BigDecimal("11.00", MathContext.DECIMAL32)), 0),
                new Movie("The Batman", Genre.ACTION, Duration.ofMinutes(95), new Money(new BigDecimal("9.00", MathContext.DECIMAL32)), 0)).collect(Collectors.toSet());        		
    }

	@Override
	public Set<Movie> getAllRecreationEvent() {
		return movies;
	}


	@Override
	public Optional<Movie> getRecreationEvent(final String title) {
		if (Objects.nonNull(title))		
			return movies.stream().filter(movie -> movie.getTitle().equals(title)).findFirst();
		return Optional.empty();		
	}

	public Set<Movie> getSpecialCodeMovies(final int specialCode) {
		return movies.stream().filter(movie -> movie.getSpecialCode() == specialCode).collect(Collectors.toSet());
	}

}
