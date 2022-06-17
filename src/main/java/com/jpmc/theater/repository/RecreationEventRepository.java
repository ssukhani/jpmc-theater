package com.jpmc.theater.repository;

import java.util.Optional;
import java.util.Set;

import org.springframework.stereotype.Repository;

import com.jpmc.theater.model.RecreationEvent;

@Repository
public interface RecreationEventRepository<T extends RecreationEvent> {

	Set<T> getAllRecreationEvent();

	Optional<T> getRecreationEvent(final String title);

}