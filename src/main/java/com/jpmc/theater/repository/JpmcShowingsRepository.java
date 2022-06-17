package com.jpmc.theater.repository;


import com.jpmc.theater.model.Movie;
import com.jpmc.theater.model.Showing;
import com.jpmc.theater.model.TimeSlot;

import static com.jpmc.theater.util.Constants.SHOWING_CAPACITY;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Repository;

@Repository
public class JpmcShowingsRepository implements ShowingsRepository <Movie>  {

	private final Set<Showing<Movie>> showings;
	
	public JpmcShowingsRepository(final RecreationEventRepository<Movie> movieRepo,
			final List<LocalDateTime> timeSlots) {

		showings = new HashSet<>(timeSlots.size());
		final List<Movie> currMovieSet = new ArrayList<>(movieRepo.getAllRecreationEvent());
		for (int i = 0; i < timeSlots.size(); i++) {
			final Movie movieToShow = currMovieSet.get(i % currMovieSet.size()); // Use Mod operator to keep rotating
																					// the list of registered movies
			showings.add(new Showing<Movie>(movieToShow,
					TimeSlot.newTimeSlotInstance(timeSlots.get(i), movieToShow.getDuration()), i, SHOWING_CAPACITY));
		}
	}
    	
	@Override
	public Set<Showing<Movie>> fetchAllShowings() {
		return showings;
	}   	
 
}
