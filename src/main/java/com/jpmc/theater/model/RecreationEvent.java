package com.jpmc.theater.model;

import java.time.Duration;
import java.util.Objects;
/**
 * This class represents an generalization of recreational events which charges a price and 
 * a has title and fixed duration
 * @see Movie, Showing 
 * @author ssukhani
 *
 */
public abstract class RecreationEvent {

	private final String title;
	private final Duration duration;
	
	private final Money basePrice;
	private String description;
	
	RecreationEvent(String title, Duration duration, Money price) {
		super();
		this.title = title;
		this.duration = duration;
		this.basePrice = price;
	}

	public String getTitle() {
		return title;
	}

	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}

	public Duration getDuration() {
		return duration;
	}
	
	public Money getBasePrice() {
		return basePrice;
	}

	@Override
	public int hashCode() {
		return Objects.hash(title, duration);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RecreationEvent other = (RecreationEvent) obj;
		return Objects.equals(title, other.title) && Objects.equals(duration, other.duration);
	}

	@Override
	public String toString() {
		return "RecreationEvent [title=" + title + ", duration="
				+ duration + "]";
	}	
}
