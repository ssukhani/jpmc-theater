package com.jpmc.theater.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.jpmc.theater.model.Money;
import com.jpmc.theater.model.RecreationEvent;
import com.jpmc.theater.model.Showing;
import com.jpmc.theater.model.Ticket;

@Service
public interface ReservationService<R extends RecreationEvent> {
	
	Integer checkAvailability(Showing<R> showing);
	
	List<Ticket<R>> reserveShowing(Showing<R> showing, int audienceCount);
	
	Ticket<R> reserveShowing(Showing<R> show);
		
	Money cancel(List<Ticket<R>> tickets);
	
}
