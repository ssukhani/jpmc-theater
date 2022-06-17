package com.jpmc.theater.model;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.Objects;

import static com.jpmc.theater.util.Constants.DEFAULT_CURRENCY_CODE;

public class Money {
	
	final double amount;
	final Currency currency;
	
	public Money(final String amount) {
		this(Double.valueOf(amount), Currency.getInstance(DEFAULT_CURRENCY_CODE));
	}

	public Money(final double amount) {
		this(amount, Currency.getInstance(DEFAULT_CURRENCY_CODE));
	}
	
	public Money(final BigDecimal amount) {
		this(amount.doubleValue(), Currency.getInstance(DEFAULT_CURRENCY_CODE));
	}
	
	public Money(double amount, Currency currency) {
		this.amount = amount;
		this.currency = currency;
	}

	public double getAmount() {
		return amount;
	}

	public Currency getCurrency() {
		return currency;
	}

	@Override
	public String toString() {
		return "Money [amount=" + amount + ", currency=" + currency + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(amount, currency);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Money other = (Money) obj;
		return Double.doubleToLongBits(amount) == Double.doubleToLongBits(other.amount)
				&& Objects.equals(currency, other.currency);
	}
	
}
