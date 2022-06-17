package com.jpmc.theater.service;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.math.MathContext;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.jpmc.theater.model.Genre;
import com.jpmc.theater.model.Money;
import com.jpmc.theater.model.Movie;
import com.jpmc.theater.model.Showing;
import com.jpmc.theater.model.TimeSlot;
import com.jpmc.theater.repository.ShowingsRepository;

@ExtendWith(MockitoExtension.class)
class JpmcTheaterServiceTest {

	@InjectMocks
    JpmcTheaterService theaterService;

	@Mock
	ShowingsRepository<Movie> showingsRepo;	
	
	@Test
	void testFetchShows() {
        int sequence = 1;      
		Set<Showing<Movie>> mockShowings = Stream.of(
						new Showing<Movie>(
								new Movie("Spider-Man: No Way Home", Genre.SCIFI, Duration.ofMinutes(90),
										new Money(new BigDecimal("12.50", MathContext.DECIMAL32)), 1),
								TimeSlot.newTimeSlotInstance(LocalDateTime.now(), 100), sequence++, 45),
						new Showing<Movie>(
								new Movie("Turning Red", Genre.FAMILY, Duration.ofMinutes(85),
										new Money(new BigDecimal("11.00", MathContext.DECIMAL32)), 0),
								TimeSlot.newTimeSlotInstance(LocalDateTime.now(), 100), sequence++, 45),
						new Showing<Movie>(
								new Movie("The Batman", Genre.ACTION, Duration.ofMinutes(95),
										new Money(new BigDecimal("9.00", MathContext.DECIMAL32)), 0),
								TimeSlot.newTimeSlotInstance(LocalDateTime.now(), 100), sequence, 45))
						.collect(Collectors.toSet());		
		
        Mockito.when(showingsRepo.fetchAllShowings()).thenReturn(mockShowings);

		final Set<Showing<Movie>> movieShowings = theaterService.fetchShows();
    	
        assertTrue(movieShowings.size() == mockShowings.size());
        assertEquals(mockShowings, movieShowings);
	}	

	@Test
	void testFetchShowLocalTimeDuration() {
		int sequence = 2;
		Set<Showing<Movie>> mockShowings = Stream
				.of(new Showing<Movie>(
						new Movie("Turning Red", Genre.FAMILY, Duration.ofMinutes(85),
								new Money(new BigDecimal("11.00", MathContext.DECIMAL32)), 0),
						TimeSlot.newTimeSlotInstance(LocalDateTime.of(2022, 6,17, 11,0), 100), sequence, 45))
				.collect(Collectors.toSet());

		Mockito.when(showingsRepo.fetchAllShowings()).thenReturn(mockShowings);
		
		Set<Showing<Movie>> movieShowings = theaterService.fetchShow(LocalTime.of(10, 45), Duration.ofMinutes(30));
		assertTrue(movieShowings.size() == mockShowings.size());
		assertEquals(mockShowings, movieShowings);
	}

	@Test
	void testFetchShowStringLocalTimeDuration() {
		int sequence = 2;
		Set<Showing<Movie>> mockShowings = Stream
				.of(new Showing<Movie>(
						new Movie("The Batman", Genre.ACTION, Duration.ofMinutes(85),
								new Money(new BigDecimal("13.00", MathContext.DECIMAL32)), 0),
						TimeSlot.newTimeSlotInstance(LocalDateTime.of(2022, 6,17, 13,0), 100), sequence, 45))
				.collect(Collectors.toSet());

		Mockito.when(showingsRepo.fetchAllShowings()).thenReturn(mockShowings);
		
		Set<Showing<Movie>> movieShowings = theaterService.fetchShow("The Batman", LocalTime.of(12, 35), Duration.ofMinutes(30));
		assertTrue(movieShowings.size() == mockShowings.size());
		assertEquals(mockShowings, movieShowings);
	}
}
