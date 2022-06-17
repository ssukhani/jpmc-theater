package com.jpmc.theater.config;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.jpmc.theater.model.Movie;
import com.jpmc.theater.service.DayOfMonthPriceDiscounter;
import com.jpmc.theater.service.PriceDiscounter;
import com.jpmc.theater.service.SequencePriceDiscounter;
import com.jpmc.theater.service.ShowTimePriceDiscounter;
import com.jpmc.theater.service.SpecialCodePriceDiscounter;


@Configuration
public class JpmcTheaterApplicationConfig {
	
	@Bean
	public PriceDiscounter<Movie> priceDiscounter() {
		return new SequencePriceDiscounter(
				new ShowTimePriceDiscounter(new SpecialCodePriceDiscounter(new DayOfMonthPriceDiscounter(null))));
	}
    
    @Bean
    public ObjectMapper customJson() {

        return new Jackson2ObjectMapperBuilder()
            .indentOutput(true)
            .propertyNamingStrategy(PropertyNamingStrategy.UPPER_CAMEL_CASE)
            .build();
    }
    
    @Bean
    public List<LocalDateTime> timeSlots() {    	
    	final LocalDate currentDate  = LocalDate.now();    	
        return List.of(LocalDateTime.of(currentDate, LocalTime.of(9, 0)),
                LocalDateTime.of(currentDate, LocalTime.of(11, 0)),
                LocalDateTime.of(currentDate, LocalTime.of(12, 50)),
                LocalDateTime.of(currentDate, LocalTime.of(14, 30)),
                LocalDateTime.of(currentDate, LocalTime.of(16, 10)),
                LocalDateTime.of(currentDate, LocalTime.of(17, 50)),
                LocalDateTime.of(currentDate, LocalTime.of(19, 30)),
                LocalDateTime.of(currentDate, LocalTime.of(21, 10)),
                LocalDateTime.of(currentDate, LocalTime.of(23, 0)));
    }

}
