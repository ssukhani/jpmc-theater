package com.jpmc.theater.service;

import java.util.Set;

import com.jpmc.theater.model.Movie;
import com.jpmc.theater.model.RecreationEvent;
import com.jpmc.theater.model.Showing;
import com.jpmc.theater.model.Ticket;

/**
 * TODO Add as aspect to print the object in requested format
 * @author ssukhani
 *
 * @param <R>
 */
public interface PrintService<R extends RecreationEvent> {

	String print(Set<Showing<Movie>> movieShowings);
	
	String print(Ticket<R> ticket);
	
	String print(R recreationEvent);
	
}
