package com.jpmc.theater.service;

import com.jpmc.theater.model.Money;
import com.jpmc.theater.model.RecreationEvent;
import com.jpmc.theater.model.Showing;

/**
 * Compute the applicable discount for a given Showing. Its implemented as a Decorator so each 
 * discounter computes the discount and compares with its decorated discounter value before returning. 
 * @author ssukhani
 *
 * @param <R>
 */
public interface PriceDiscounter <R extends RecreationEvent> {
	Money computeDiscount(Showing<R> eventShowing);
}
