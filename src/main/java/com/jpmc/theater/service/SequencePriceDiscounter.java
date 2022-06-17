package com.jpmc.theater.service;

import static com.jpmc.theater.util.Constants.SEQUENCE_DISCOUNT_FIRST_SHOW;
import static com.jpmc.theater.util.Constants.SEQUENCE_DISCOUNT_SECOND_SHOW;

import java.math.BigDecimal;

import com.jpmc.theater.model.Money;
import com.jpmc.theater.model.Movie;
import com.jpmc.theater.model.Showing;

/**
 * Price discounter based on Showing sequence, per requirement only seq 1 and 2 gets a discount 
 * @author ssukhani
 *
 */
public class SequencePriceDiscounter implements PriceDiscounter<Movie> {
	
	private final PriceDiscounter<Movie> decoDiscounter;	
	
	public SequencePriceDiscounter(PriceDiscounter<Movie> decoDiscounter) {
		this.decoDiscounter = decoDiscounter;
	}
	
	@Override
	public Money computeDiscount(Showing<Movie> showing) {
		Money showDiscount = decoDiscounter != null ? decoDiscounter.computeDiscount(showing) : new Money(BigDecimal.ZERO);

		// Show sequence Discount, fixed amount based on sequence
		if (showing.getSequenceOfTheDay() == 1) {
			showDiscount = SEQUENCE_DISCOUNT_FIRST_SHOW.doubleValue() > showDiscount.getAmount()
					? new Money(SEQUENCE_DISCOUNT_FIRST_SHOW)
					: showDiscount; // $3 discount for 1st show
		} else if (showing.getSequenceOfTheDay() == 2) {
			showDiscount = SEQUENCE_DISCOUNT_SECOND_SHOW.doubleValue() > showDiscount.getAmount()
					? new Money(SEQUENCE_DISCOUNT_SECOND_SHOW)
					: showDiscount; // $2 discount for 2nd show
		}
		return showDiscount;
	}

}
