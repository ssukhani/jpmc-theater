package com.jpmc.theater;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.math.MathContext;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.jpmc.theater.config.JpmcTheaterApplicationConfig;
import com.jpmc.theater.model.Genre;
import com.jpmc.theater.model.Money;
import com.jpmc.theater.model.Movie;
import com.jpmc.theater.model.Showing;
import com.jpmc.theater.model.TimeSlot;
import com.jpmc.theater.repository.RecreationEventRepository;
import com.jpmc.theater.service.PriceDiscounter;

class PriceDiscounterTest {

    private static PriceDiscounter<Movie> priceDiscounter;

    @BeforeAll
    static void setUp(){
        ApplicationContext context = new AnnotationConfigApplicationContext(JpmcTheaterApplicationConfig.class);
        priceDiscounter = context.getBean(PriceDiscounter.class);
    }

	@Test
	void testFirstInSequenceComputeDiscount() {
		Movie spiderMan = new Movie("Spider-Man: No Way Home", Genre.SCIFI, Duration.ofMinutes(90), new Money(new BigDecimal("12.50", MathContext.DECIMAL32)), 1);
		TimeSlot movieSlot = TimeSlot.newTimeSlotInstance(LocalDateTime.of(LocalDate.now(), LocalTime.of(9, 0)), 90l);				
		
		// Max discount applicable is $3 due to first in sequence
		Money discount = priceDiscounter.computeDiscount(new Showing<> (spiderMan, movieSlot, 1, 50));
		assertEquals(new Money(new BigDecimal("3")), discount);			
	}

	@Test
	void testSecondInSequenceComputeDiscount() {
		Movie turningRed = new Movie("Turning Red", Genre.FAMILY, Duration.ofMinutes(85), new Money(new BigDecimal("11.00", MathContext.DECIMAL32)), 0);
		TimeSlot movieSlot = TimeSlot.newTimeSlotInstance(LocalDateTime.of(LocalDate.now(), LocalTime.of(9, 0)), 90l);				
		
		// Max discount applicable is $2 due to second in sequence
		Money discount = priceDiscounter.computeDiscount(new Showing<> (turningRed, movieSlot, 2, 50));
		assertEquals(new Money(new BigDecimal("2.00")), discount);			
	}
	
	@Test
	void testSpecialCodeComputeDiscount() {
		Movie spiderMan = new Movie("Spider-Man: No Way Home", Genre.SCIFI, Duration.ofMinutes(90), new Money(new BigDecimal("12.50", MathContext.DECIMAL32)), 1);
		TimeSlot movieSlot = TimeSlot.newTimeSlotInstance(LocalDateTime.of(LocalDate.now(), LocalTime.of(9, 30)), 90l);				
		
		// Max discount applicable is $2.50 due to 20% discount for being special code 1
		Money discount = priceDiscounter.computeDiscount(new Showing<> (spiderMan, movieSlot, 4, 50));
		assertEquals(new Money(new BigDecimal("2.50")), discount);			
	}
	
	@Test
	void testShowtimeComputeDiscount() {
		Movie spiderMan = new Movie("Spider-Man: No Way Home", Genre.SCIFI, Duration.ofMinutes(90), new Money(new BigDecimal("12.50", MathContext.DECIMAL32)), 1);
		TimeSlot movieSlot = TimeSlot.newTimeSlotInstance(LocalDateTime.of(LocalDate.now(), LocalTime.of(11, 30)), 90l);				
		
		// Max discount applicable is $3.125 due to 25% discount for starting between 11AM and 4PM
		Money discount = priceDiscounter.computeDiscount(new Showing<> (spiderMan, movieSlot, 4, 50));
		assertEquals(new Money(new BigDecimal("3.125")), discount);			
	}

	@Test
	void testDaySevenComputeDiscount() {
		Movie spiderMan = new Movie("Spider-Man: No Way Home", Genre.SCIFI, Duration.ofMinutes(90), new Money(new BigDecimal("12.50", MathContext.DECIMAL32)), 0);
		TimeSlot movieSlot = TimeSlot.newTimeSlotInstance(LocalDateTime.of(LocalDate.of(2022, 6, 7), LocalTime.of(16, 30)), 90l);				
		
		// Max discount applicable is $1 due to today being 7th
		Money discount = priceDiscounter.computeDiscount(new Showing<> (spiderMan, movieSlot, 4, 50));
		assertEquals(new Money(new BigDecimal("1.0")), discount);			
	}
}
