package com.jpmc.theater.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.jpmc.theater.model.Money;
import com.jpmc.theater.model.Movie;
import com.jpmc.theater.model.Showing;
import com.jpmc.theater.model.Ticket;

import com.jpmc.theater.repository.ShowingsRepository;
import com.jpmc.theater.repository.TicketRepository;

@Service
public class JpmcReservationService implements ReservationService<Movie> {

	private ShowingsRepository<Movie> showingsRepo;
	private TicketRepository<Movie> ticketsRepo;
	private PriceDiscounter<Movie> priceDiscounter;
	
	public JpmcReservationService(ShowingsRepository<Movie> showingsRepo, TicketRepository<Movie> ticketsRepo,
			PriceDiscounter<Movie> priceDiscounter) {
		this.showingsRepo = showingsRepo;
		this.ticketsRepo = ticketsRepo;
		this.priceDiscounter = priceDiscounter;
	}

	@Override
	public Integer checkAvailability(Showing<Movie> showing) {
		Set<Showing<Movie>> showings = showingsRepo.fetchAllShowings();
		return showings.stream().filter(listedShowing -> showing.equals(showing)).findFirst()
				.map(reqShowing -> ticketsRepo.getAvailableTickets(showing)).get();
	}

	@Override
	public List<Ticket<Movie>> reserveShowing(Showing<Movie> show, int audienceCount) {
		return ticket(show, audienceCount);
	}
	
	@Override
	public Ticket<Movie> reserveShowing(Showing<Movie> show) {
		return ticket(show, 1).get(0);
	}


	@Override
	public Money cancel(List<Ticket<Movie>> tickets) {
		// TODO Auto-generated method stub
		return null;
	}
	
	private List<Ticket<Movie>> ticket(Showing<Movie> showing, int count) {

		List<Ticket<Movie>> bookedTicket = new ArrayList<>();
		Integer availability = checkAvailability(showing);
		if (availability >= count) {
			for (int i = 0; i < count; i++) {
				bookedTicket.add(new Ticket<Movie>(showing, showing.getRecreationEvent().getBasePrice(),
						priceDiscounter.computeDiscount(showing)));
			}
			ticketsRepo.reduceAvailableTicket(bookedTicket);
		}
		return bookedTicket;
	}


}
