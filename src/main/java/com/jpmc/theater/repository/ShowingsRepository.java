package com.jpmc.theater.repository;

import java.util.Set;

import org.springframework.stereotype.Repository;

import com.jpmc.theater.model.RecreationEvent;
import com.jpmc.theater.model.Showing;

@Repository
public interface ShowingsRepository<T extends RecreationEvent> {

	Set<Showing<T>> fetchAllShowings();

}