package com.jpmc.theater.model;

import java.util.Objects;

public class Showing<R extends RecreationEvent> {
	
	private final RecreationEvent recreationEvent;
	private final TimeSlot slot;

    private final int sequenceOfTheDay;
    private final int capacity;
    
	public Showing(RecreationEvent recreationEvent, TimeSlot slot, int sequenceOfTheDay, int capacity) {
		super();
		this.recreationEvent = recreationEvent;
		this.slot = slot;
		this.sequenceOfTheDay = sequenceOfTheDay;
		this.capacity = capacity;
	}

	public RecreationEvent getRecreationEvent() {
		return recreationEvent;
	}

	public TimeSlot getSlot() {
		return slot;
	}

	public int getSequenceOfTheDay() {
		return sequenceOfTheDay;
	}

	public int getCapacity() {
		return capacity;
	}

	@Override
	public int hashCode() {
		return Objects.hash(capacity, recreationEvent, sequenceOfTheDay, slot);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Showing<?> other = (Showing<?>) obj;
		return capacity == other.capacity && Objects.equals(recreationEvent, other.recreationEvent)
				&& sequenceOfTheDay == other.sequenceOfTheDay && Objects.equals(slot, other.slot);
	}

	@Override
	public String toString() {
		return "Showing [recreationEvent=" + recreationEvent + ", slot=" + slot + ", sequenceOfTheDay="
				+ sequenceOfTheDay + ", capacity=" + capacity + "]";
	}	
}
