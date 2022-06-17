package com.jpmc.theater.service;

import static com.jpmc.theater.util.Constants.SPECIAL_MOVIE_DISCOUNT_MULTIPLIER;

import java.math.BigDecimal;

import com.jpmc.theater.model.Money;
import com.jpmc.theater.model.Movie;
import com.jpmc.theater.model.Showing;
import com.jpmc.theater.util.Constants;

/**
 * Price discounter based on Special code associated to the showing, special code value currently 
 * defined in Constants.MOVIE_CODE_SPECIAL
 * @author ssukhani
 *
 */
public class SpecialCodePriceDiscounter implements PriceDiscounter<Movie> {
	
	private final PriceDiscounter<Movie> decoDiscounter;	
	
	public SpecialCodePriceDiscounter(PriceDiscounter<Movie> decoDiscounter) {
		this.decoDiscounter = decoDiscounter;
	}
	
	@Override
	public Money computeDiscount(Showing<Movie> showing) {
		Money showDiscount = decoDiscounter != null ? decoDiscounter.computeDiscount(showing) : new Money(BigDecimal.ZERO);
		
		// Special Movie Code Discount @ 20%
		final Movie movie = (Movie) showing.getRecreationEvent();
		if (movie.getSpecialCode() == Constants.MOVIE_CODE_SPECIAL) {
			BigDecimal codeDiscount = SPECIAL_MOVIE_DISCOUNT_MULTIPLIER
					.multiply(BigDecimal.valueOf(movie.getBasePrice().getAmount())); // Discount for special movie
			showDiscount = codeDiscount.doubleValue() > showDiscount.getAmount() ? new Money(codeDiscount)
					: showDiscount;
		}
		return showDiscount;
	}

}
