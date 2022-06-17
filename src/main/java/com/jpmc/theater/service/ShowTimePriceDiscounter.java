package com.jpmc.theater.service;

import static com.jpmc.theater.util.Constants.START_TIME_DISCOUNT;
import static com.jpmc.theater.util.Constants.START_TIME_DISCOUNT_END;
import static com.jpmc.theater.util.Constants.START_TIME_DISCOUNT_MULTIPLIER;

import java.math.BigDecimal;
import java.time.LocalTime;

import com.jpmc.theater.model.Money;
import com.jpmc.theater.model.Movie;
import com.jpmc.theater.model.Showing;

/**
 * Price discounter based on start time of the Showing, discount applicable for movies starting 
 * between 11AM to 4PM
 * @author ssukhani
 *
 */
public class ShowTimePriceDiscounter implements PriceDiscounter<Movie> {
	
	private final PriceDiscounter<Movie> decoDiscounter;
	
	public ShowTimePriceDiscounter(PriceDiscounter<Movie> decoDiscounter) {
		this.decoDiscounter = decoDiscounter;
	}
	
	@Override
	public Money computeDiscount(Showing<Movie> showing) {
		Money showDiscount = decoDiscounter != null ? decoDiscounter.computeDiscount(showing)
				: new Money(BigDecimal.ZERO);

		BigDecimal showBasePrice = BigDecimal.valueOf(showing.getRecreationEvent().getBasePrice().getAmount());
		LocalTime startTime = showing.getSlot().getStartTime().toLocalTime();
		// Discount if starting between start time and end time
		if (startTime.isAfter(START_TIME_DISCOUNT) && startTime.isBefore(START_TIME_DISCOUNT_END)) {
			final BigDecimal showTimeDiscount = showBasePrice.multiply(START_TIME_DISCOUNT_MULTIPLIER);
			showDiscount = showTimeDiscount.doubleValue() > showDiscount.getAmount() ? new Money(showTimeDiscount)
					: showDiscount;
		}
		return showDiscount;
	}

}
