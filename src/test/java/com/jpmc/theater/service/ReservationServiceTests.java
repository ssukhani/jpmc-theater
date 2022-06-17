package com.jpmc.theater.service;

import com.jpmc.theater.model.Genre;
import com.jpmc.theater.model.Money;
import com.jpmc.theater.model.Movie;
import com.jpmc.theater.model.Showing;
import com.jpmc.theater.model.Ticket;
import com.jpmc.theater.model.TimeSlot;
import com.jpmc.theater.repository.ShowingsRepository;
import com.jpmc.theater.repository.TicketRepository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.jpmc.theater.util.Constants.MOVIE_CODE_SPECIAL;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class ReservationServiceTests {

	@InjectMocks
	JpmcReservationService reservationServiceImpl;

	@Mock
	ShowingsRepository<Movie> showingsRepo;

	@Mock
	TicketRepository<Movie> ticketsRepo;

	@Mock
	PriceDiscounter<Movie> priceDiscounter;    
    
    @Test
    void reservationMultiSuccessful() {
        int audienceCount = 3;
        int sequence = 1;      
		var showing = new Showing<Movie>(
				new Movie("Spider-Man: No Way Home", Genre.SCIFI, Duration.ofMinutes(90),
						new Money("12.5"), MOVIE_CODE_SPECIAL),
				TimeSlot.newTimeSlotInstance(LocalDateTime.now(), 100), sequence, 45);
		
		var price = new Money(12.5);
		var discount = new Money(2);
        
		List<Ticket<Movie>> tickets = List.of(new Ticket<Movie>(showing, price, discount),
				new Ticket<Movie>(showing, price, discount), new Ticket<Movie>(showing, price, discount));
		
		Mockito.when(showingsRepo.fetchAllShowings()).thenReturn(Stream.of(showing).collect(Collectors.toSet()));
        Mockito.when(reservationServiceImpl.checkAvailability(showing)).thenReturn(3);
        Mockito.when(ticketsRepo.reduceAvailableTicket(Mockito.anyList())).thenReturn(tickets.size());

    	List<Ticket<Movie>> ticketsReserved = reservationServiceImpl.reserveShowing(showing, audienceCount);
    	
        assertTrue(ticketsReserved.size() == audienceCount);
    }  
    
    @Test
    void reservationSingleSuccessful() {
        int sequence = 1;      
		var showing = new Showing<Movie>(
				new Movie("Spider-Man: No Way Home", Genre.SCIFI, Duration.ofMinutes(90),
						new Money("12.5"), MOVIE_CODE_SPECIAL),
				TimeSlot.newTimeSlotInstance(LocalDateTime.now(), 100), 1, 45);
		
		var price = new Money(12.5);
		var discount = new Money(2);
        
		List<Ticket<Movie>> tickets = List.of(new Ticket<Movie>(showing, price, discount));

		Mockito.when(showingsRepo.fetchAllShowings()).thenReturn(Stream.of(showing).collect(Collectors.toSet()));
        Mockito.when(reservationServiceImpl.checkAvailability(showing)).thenReturn(3);
        Mockito.when(ticketsRepo.reduceAvailableTicket(Mockito.anyList())).thenReturn(tickets.size());

        Ticket<Movie> ticketsReserved = reservationServiceImpl.reserveShowing(showing);
    	
        
        assertNotNull(ticketsReserved);
        assertEquals(sequence, ticketsReserved.getShowing().getSequenceOfTheDay());
    } 
}
