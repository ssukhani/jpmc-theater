package com.jpmc.theater.service;

import java.time.Duration;
import java.time.LocalTime;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.jpmc.theater.model.RecreationEvent;
import com.jpmc.theater.model.Showing;

/**
 * This would be the customer facing service that would provide details for Available showings and ability to reserve a
 * show. It could be further extended as need be. 
 * 
 * Can be implemented as EntryPoint for web based or as standalone application with predefined workflow. 
 * @author ssukhani
 *
 * @param <R>
 */
@Service
public interface TheaterService<R extends RecreationEvent> {

	Set<Showing<R>> fetchShows();
	
	Set<Showing<R>> fetchShow(LocalTime startTime, Duration followingMins);
	
	Set<Showing<R>> fetchShow(String title, LocalTime startTime, Duration followingMins);
	
    void printMovieSchedule(); 

}
