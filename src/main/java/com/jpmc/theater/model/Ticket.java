package com.jpmc.theater.model;

import java.util.Objects;

public class Ticket<R extends RecreationEvent> {
	
	private final Money price;
	private final Money discount;	
	
	private final Showing<R> showing;

	public Ticket(Showing<R> showing, Money price, Money discount) {
		super();
		this.price = price;
		this.discount = discount;
		this.showing = showing;
	}

	public Money getPrice() {
		return price;
	}

	public Money getDiscount() {
		return discount;
	}

	public Showing<R> getShowing() {
		return showing;
	}

	public Money getNetPrice() {
		if (Objects.nonNull(discount))
			return new Money(getPrice().getAmount() + discount.getAmount());
		return price;
	}
	
	@Override
	public String toString() {
		return "Ticket [price=" + price + ", discount=" + discount + ", showing=" + showing + "]";
	}	
}
