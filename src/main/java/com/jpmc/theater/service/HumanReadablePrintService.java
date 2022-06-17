package com.jpmc.theater.service;

import java.time.LocalDate;
import java.util.Set;

import org.springframework.stereotype.Component;

import com.jpmc.theater.model.Movie;
import com.jpmc.theater.model.Showing;
import com.jpmc.theater.model.Ticket;
import com.jpmc.theater.util.StringUtil;

@Component("human")
public class HumanReadablePrintService implements PrintService<Movie> {

	private final static String LINE_SEPARATOR = System.lineSeparator();
	
	@Override
	public String print(Set<Showing<Movie>> showings) {
		final StringBuffer buff = new StringBuffer();
		buff.append("Current Date: " + LocalDate.now());
		buff.append("===================================================");
        showings.forEach(s -> buff.append(s.getSequenceOfTheDay() + ": " + s.getSlot().getStartTime() + " " + s.getRecreationEvent().getTitle() + " "
                        + StringUtil.humanReadableFormat(s.getRecreationEvent().getDuration()) 
                        + " " + s.getRecreationEvent().getBasePrice() + LINE_SEPARATOR));
        buff.append("===================================================");
        System.out.println(buff);
		return buff.toString();
	}

	@Override
	public String print(Ticket<Movie> ticket) {
		System.out.println(ticket);
		return ticket.toString();
	}

	@Override
	public String print(Movie recreationEvent) {
		System.out.println(recreationEvent);
		return recreationEvent.toString();
	}

}
