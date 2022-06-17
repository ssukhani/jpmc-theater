package com.jpmc.theater.model;

import java.util.List;
import java.util.Optional;

public class Reservation <R extends RecreationEvent>{
	
	private final Customer customer;
	private final Showing<R> showing;
		
	private List<Ticket<R>> tickets;

	public Reservation(Customer customer, Showing<R> showing) {
		this.customer = customer;
		this.showing = showing;
	}

	public List<Ticket<R>> getTickets() {
		return tickets;
	}

	public void setTickets(List<Ticket<R>> tickets) {
		this.tickets = tickets;
	}

	public Customer getCustomer() {
		return customer;
	}

	public Showing<R> getShowing() {
		return showing;
	}
	
	public Optional<Money> getCost() {
		if(tickets != null && !tickets.isEmpty()) {
			return Optional.of(new Money(tickets.get(0).getNetPrice().getAmount() * tickets.size()));
		}
		return Optional.empty();
	}
	
	

	@Override
	public String toString() {
		return "Reservation [customer=" + customer + ", showing=" + showing + ", tickets=" + tickets + "]";
	}

}
