package com.jpmc.theater.service;

import static com.jpmc.theater.util.Constants.SPECIAL_MOVIE_DISCOUNT_DAY_SEVEN;
import static com.jpmc.theater.util.Constants.SPECIAL_DISCOUNT_SEEVENTH_DAY;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.jpmc.theater.model.Money;
import com.jpmc.theater.model.Movie;
import com.jpmc.theater.model.Showing;

/**
 * Price discounter based on 7th day of month
 * @author ssukhani
 *
 */
public class DayOfMonthPriceDiscounter implements PriceDiscounter<Movie> {
	
	private final PriceDiscounter<Movie> decoDiscounter;	
	
	public DayOfMonthPriceDiscounter(PriceDiscounter<Movie> decoDiscounter) {
		this.decoDiscounter = decoDiscounter;
	}
	
	@Override
	public Money computeDiscount(Showing<Movie> showing) {
		Money showDiscount = decoDiscounter != null ? decoDiscounter.computeDiscount(showing) : new Money(BigDecimal.ZERO);
		// $1 Discount if today happens to be 7th day of the Month
		if (LocalDate.of(2022, 6, 7).getDayOfMonth() == SPECIAL_MOVIE_DISCOUNT_DAY_SEVEN) {
			showDiscount = SPECIAL_DISCOUNT_SEEVENTH_DAY.doubleValue() > showDiscount.getAmount()
					? new Money(SPECIAL_DISCOUNT_SEEVENTH_DAY)
					: showDiscount;
		}
		return showDiscount;
	}

}
