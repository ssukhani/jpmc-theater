package com.jpmc.theater.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.jpmc.theater.model.RecreationEvent;
import com.jpmc.theater.model.Showing;
import com.jpmc.theater.model.Ticket;
/**
 * TicketRepository keeps track of available tickets for each show, 
 * adding/reducing as tickets are cancelled or booked.
 * 
 * **The implementation should be made thread safe for each of the operation in multi threaded env.**
 * @author ssukhani
 *
 * @param <R>
 */
@Repository
public interface TicketRepository<R extends RecreationEvent> {
	
	void initializeAvailableTicketInTheater();
	
	int getAvailableTickets(Showing<R> showing);
	
	int addToAvailableTickets(Ticket<R> cancelledTicket);
	
	int addAvailableTicket(List<Ticket<R>> cancelledTicket);

	int reduceAvailableTicket(Ticket<R> soldTicket);
	
	int reduceAvailableTicket(List<Ticket<R>> soldTickets);
	
}
