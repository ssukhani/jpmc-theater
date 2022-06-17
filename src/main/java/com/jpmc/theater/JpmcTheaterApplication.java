package com.jpmc.theater;

import java.time.Duration;
import java.time.LocalTime;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;

import com.jpmc.theater.model.Movie;
import com.jpmc.theater.model.Showing;
import com.jpmc.theater.model.Ticket;
import com.jpmc.theater.service.JpmcReservationService;
import com.jpmc.theater.service.JpmcTheaterService;
import com.jpmc.theater.service.PrintService;

import static com.jpmc.theater.util.Constants.DEFAULT_PRINT_FORMAT;

@SpringBootApplication
public class JpmcTheaterApplication implements CommandLineRunner {

	@Autowired
	private ApplicationContext context;
	
	public static void main(String[] args) {
		ConfigurableApplicationContext ctx = SpringApplication.run(JpmcTheaterApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		PrintService<Movie> printService = context.getBean(DEFAULT_PRINT_FORMAT, PrintService.class);
		
		JpmcTheaterService theaterService = context.getBean(JpmcTheaterService.class);
		//	Find available show in next 30 mins
		Set<Showing<Movie>> movieShowings = theaterService.fetchShow(LocalTime.now(), Duration.ofMinutes(90));
		
		// Print the output in Json format, need to correct LocalTime and Duration formatting.
		printService.print(movieShowings);
		
		// Iterate over the shows, and book a ticket on first availability
		for (Showing<Movie> movieShow : movieShowings) {
		// Place the reservation for the available show
			JpmcReservationService reservationService = context.getBean(JpmcReservationService.class);
			Ticket<Movie> bookedTicket = reservationService.reserveShowing(movieShow);	
			
			if (bookedTicket != null) {
				return;
			}
		}
		
	}

}
