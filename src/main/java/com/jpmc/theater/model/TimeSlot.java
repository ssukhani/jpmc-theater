package com.jpmc.theater.model;

import java.time.LocalDateTime;
import java.util.Objects;
import java.time.Duration;

public class TimeSlot {
	
	final LocalDateTime startTime;	
	final LocalDateTime endTime;
	
	public static TimeSlot newTimeSlotInstance(final LocalDateTime startTime, final long durationInMins) {
		return new TimeSlot(startTime, startTime.plusMinutes(durationInMins));
	}

	public static TimeSlot newTimeSlotInstance(final LocalDateTime startTime, final Duration duration) {
		return new TimeSlot(startTime, startTime.plus(duration));
	}
	
	TimeSlot(LocalDateTime startTime, LocalDateTime endTime) {
		super();
		this.startTime = startTime;
		this.endTime = endTime;
	}

	public LocalDateTime getStartTime() {
		return startTime;
	}

	public LocalDateTime getEndTime() {
		return endTime;
	}

	final Duration getDuration() {
		return Duration.between(startTime, endTime);
	}

	@Override
	public int hashCode() {
		return Objects.hash(endTime, startTime);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TimeSlot other = (TimeSlot) obj;
		return Objects.equals(endTime, other.endTime) && Objects.equals(startTime, other.startTime);
	}

	@Override
	public String toString() {
		return "TimeSlot [startTime=" + startTime + ", endTime=" + endTime + "]";
	}	
}
