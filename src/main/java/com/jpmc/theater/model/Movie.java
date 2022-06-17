package com.jpmc.theater.model;

import java.time.Duration;
import java.util.Objects;
import java.util.Optional;
/**
 * 
 * @author ssukhani
 *
 */
public class Movie extends RecreationEvent {

	private final Genre type;

	private Optional<String> plot;
	private int specialCode;
	
	public Movie(String title, Genre type, Duration duration, Money basePrice, int specialCode) {
		super(title, duration, basePrice);
		this.plot = null;
		this.type = type; 
		this.specialCode = specialCode;
	}

	public Genre getType() {
		return type;
	}
	
	public int getSpecialCode() {
		return specialCode;
	}

	public void setSpecialCode(int speacialCode) {
		this.specialCode = speacialCode;
	}

	public Optional<String> getPlot() {
		return plot;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(specialCode, type);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Movie other = (Movie) obj;
		return specialCode == other.specialCode && type == other.type;
	}

	@Override
	public String toString() {
		return "Movie [type=" + type + ", plot=" + plot + ", specialCode=" + specialCode + ", toString()="
				+ super.toString() + "]";
	}
}
