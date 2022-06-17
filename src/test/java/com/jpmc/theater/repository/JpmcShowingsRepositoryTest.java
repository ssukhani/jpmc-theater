package com.jpmc.theater.repository;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.jpmc.theater.config.JpmcTheaterApplicationConfig;
import com.jpmc.theater.model.Movie;

class JpmcShowingsRepositoryTest {

    private static ShowingsRepository<Movie> showingSchedule;

    @BeforeAll
    static void setUp(){
    	showingSchedule = new JpmcShowingsRepository(new JpmcMovieRepository(),
    			new JpmcTheaterApplicationConfig().timeSlots());
    	
    }

    @Test
    void scheduleNotNullOrEmpty() {
        assertNotNull(showingSchedule.fetchAllShowings());
        assertNotEquals(0,  showingSchedule.fetchAllShowings().size());
        // Based on 9 time slots 
        assertEquals(9,  showingSchedule.fetchAllShowings().size());
    }
}
