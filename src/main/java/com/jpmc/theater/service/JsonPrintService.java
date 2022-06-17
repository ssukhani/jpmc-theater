package com.jpmc.theater.service;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jpmc.theater.model.Movie;
import com.jpmc.theater.model.Showing;
import com.jpmc.theater.model.Ticket;

@Component("json")
public class JsonPrintService implements PrintService<Movie> {
	
	@Autowired
	ObjectMapper jsonMapper;

	@Override
	public String print(Set<Showing<Movie>> showings) {
		try {
			String jsonStr = jsonMapper.writeValueAsString(showings);
			System.out.println(jsonStr);
			return jsonStr;
		} catch (JsonProcessingException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public String print(Ticket<Movie> ticket) {
		try {
			String jsonStr = jsonMapper.writeValueAsString(ticket);
			System.out.println(jsonStr);
			return jsonStr;
		} catch (JsonProcessingException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public String print(Movie recreationEvent) {
		try {
			String jsonStr = jsonMapper.writeValueAsString(recreationEvent);
			System.out.println(jsonStr);
			return jsonStr;
		} catch (JsonProcessingException e) {
			throw new RuntimeException(e);
		}
	}

}
