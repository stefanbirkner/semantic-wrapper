package com.github.stefanbirkner.semanticwrapper.generator;

import static java.lang.Integer.parseInt;

public class OrderNumber {
	private final Integer orderNumber;

	public static OrderNumber orderNumber(Integer orderNumber) {
		return (orderNumber == null) ? null : new OrderNumber(orderNumber);
	}

	public static OrderNumber parseOrderNumber(String text) {
		return ((text == null) || text.isEmpty()) ? null : new OrderNumber(parseInt(text));
	}

	public OrderNumber(Integer orderNumber) {
		if (orderNumber == null)
			throw new IllegalArgumentException("Cannot wrap null.");
		this.orderNumber = orderNumber;
	}

	public int intValue() {
		return orderNumber;
	}

	@Override
	public int hashCode() {
		return orderNumber.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OrderNumber other = (OrderNumber) obj;
		return (orderNumber.equals(other.orderNumber));
	}

	@Override
	public String toString() {
		return orderNumber.toString();
	}
}