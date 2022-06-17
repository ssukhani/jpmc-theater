package com.jpmc.theater.service;

import java.time.Duration;
import java.time.LocalTime;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.jpmc.theater.model.Movie;
import com.jpmc.theater.model.Showing;
import com.jpmc.theater.repository.ShowingsRepository;

@Service
public class JpmcTheaterService implements TheaterService<Movie> {	
	
	private ShowingsRepository<Movie> showingsRepo;		
		
	public JpmcTheaterService(final ShowingsRepository<Movie> showingsRepo) {
		this.showingsRepo = showingsRepo;
	}

	@Override
	public Set<Showing<Movie>> fetchShows() {
		return showingsRepo.fetchAllShowings();
	}

	@Override
	public Set<Showing<Movie>> fetchShow(LocalTime startTime, Duration followingMins) {
		Set<Showing<Movie>> allShowings = showingsRepo.fetchAllShowings();
		return allShowings.stream().filter(showing -> 
			showing.getSlot().getStartTime().toLocalTime().compareTo(startTime) >= 0
					&& showing.getSlot().getStartTime().toLocalTime().compareTo(startTime.plus(followingMins)) <= 0
		).collect(Collectors.toSet());		
	}
	
	@Override
	public Set<Showing<Movie>> fetchShow(final String title, final LocalTime startTime, final Duration followingMins) {
		Set<Showing<Movie>> allShowings = showingsRepo.fetchAllShowings();
		return allShowings.stream().filter(showing -> showing.getRecreationEvent().getTitle().equals(title) &&
			showing.getSlot().getStartTime().toLocalTime().compareTo(startTime) >= 0
					&& showing.getSlot().getStartTime().toLocalTime().compareTo(startTime.plus(followingMins)) <= 0
		).collect(Collectors.toSet());	
	}

	@Override
	public void printMovieSchedule() {
		// TODO Auto-generated method stub
		
	}	
	

}
