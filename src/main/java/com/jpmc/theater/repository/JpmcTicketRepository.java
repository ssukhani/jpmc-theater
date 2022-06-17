package com.jpmc.theater.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.jpmc.theater.model.Movie;
import com.jpmc.theater.model.Showing;
import com.jpmc.theater.model.Ticket;
import com.jpmc.theater.util.Constants;
/**
 * 
 * @author ssukhani
 *
 */
@Repository
public class JpmcTicketRepository implements TicketRepository<Movie> {
	
	private Map<Showing<Movie>, Integer> availableTickets = new HashMap<>();

	private ShowingsRepository<Movie> showingsRepo;
	
	public JpmcTicketRepository(ShowingsRepository<Movie> showingsRepo) {
		this.showingsRepo = showingsRepo;
		this.initializeAvailableTicketInTheater();
	}

	@Override
	public void initializeAvailableTicketInTheater() {
		showingsRepo.fetchAllShowings().stream().forEach(showing -> availableTickets.put(showing, Constants.SHOWING_CAPACITY));		
	}

	@Override
	public int getAvailableTickets(Showing<Movie> showing) {
		return availableTickets.get(showing);
	}

	@Override
	public int addToAvailableTickets(Ticket<Movie> cancelledTicket) {
		Integer updatedTicketCount = availableTickets.get(cancelledTicket.getShowing()) + 1;
		availableTickets.put(cancelledTicket.getShowing(), updatedTicketCount);
		return updatedTicketCount;
	}

	@Override
	public int addAvailableTicket(List<Ticket<Movie>> cancelledTickets) {
		int updatedTicketCount = 0;
		for (Ticket<Movie> cancelledTicket : cancelledTickets) {
			updatedTicketCount = addToAvailableTickets(cancelledTicket);
		}
		return updatedTicketCount;	
	}

	@Override
	public int reduceAvailableTicket(Ticket<Movie> soldTicket) {
		Integer updatedTicketCount = availableTickets.get(soldTicket.getShowing()) - 1;
		availableTickets.put(soldTicket.getShowing(), updatedTicketCount);
		return updatedTicketCount;
	}

	@Override
	public int reduceAvailableTicket(List<Ticket<Movie>> soldTickets) {
		int updatedTicketCount = 0;
		for (Ticket<Movie> soldTicket : soldTickets) {
			updatedTicketCount = addToAvailableTickets(soldTicket);
		}
		return updatedTicketCount;	
	}
}
