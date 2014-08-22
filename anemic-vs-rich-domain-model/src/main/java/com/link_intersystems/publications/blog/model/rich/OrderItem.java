package com.link_intersystems.publications.blog.model.rich;

import java.math.BigDecimal;

public class OrderItem {

	private BigDecimal price;

	private int quantity;

	private String name = "no name";

	public OrderItem(BigDecimal price, int quantity, String name) {
		if (price == null) {
			throw new IllegalArgumentException("price must not be null");
		}
		if (name == null) {
			throw new IllegalArgumentException("name must not be null");
		}
		if (price.compareTo(BigDecimal.ZERO) < 0) {
			throw new IllegalArgumentException(
					"price must be a positive big decimal");
		}
		if (quantity < 1) {
			throw new IllegalArgumentException("quantity must be 1 or greater");
		}
		this.price = price;
		this.quantity = quantity;
		this.name = name;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public int getQuantity() {
		return quantity;
	}

	public String getName() {
		return name;
	}

	/**
	 * The total is defined as the {@link #getPrice()} multiplied with the
	 * {@link #getQuantity()}.
	 * 
	 * @return
	 */
	public BigDecimal getTotal() {
		int quantity = getQuantity();
		BigDecimal price = getPrice();
		BigDecimal total = price.multiply(new BigDecimal(quantity));
		return total;
	}
}
