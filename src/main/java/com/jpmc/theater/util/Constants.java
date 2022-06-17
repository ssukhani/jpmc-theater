package com.jpmc.theater.util;

import java.math.BigDecimal;
import java.time.LocalTime;

public final class Constants {

    private Constants(){}

    public static final String DEFAULT_PRINT_FORMAT = "json";
    
    public static final int SHOWING_CAPACITY = 100;
    public static final String DEFAULT_CURRENCY_CODE = "USD";
    

    public static final int MOVIE_CODE_SPECIAL = 1;
    public static final BigDecimal SPECIAL_MOVIE_DISCOUNT_MULTIPLIER = new BigDecimal("0.2");  // 20% discount
    
    public static final BigDecimal START_TIME_DISCOUNT_MULTIPLIER = new BigDecimal("0.25");    // 25% discount
    public static final LocalTime START_TIME_DISCOUNT = LocalTime.of(11, 0); // 11AM
    public static final LocalTime START_TIME_DISCOUNT_END = LocalTime.of(16, 0); // 4PM

    public static final BigDecimal SEQUENCE_DISCOUNT_FIRST_SHOW = new BigDecimal("3");
    public static final BigDecimal SEQUENCE_DISCOUNT_SECOND_SHOW = new BigDecimal("2");
    
    public static final BigDecimal SPECIAL_DISCOUNT_SEEVENTH_DAY = new BigDecimal("1");
    public static final int SPECIAL_MOVIE_DISCOUNT_DAY_SEVEN = 7;
}
