package com.link_intersystems.blog.model.rich;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Order {

	private BigDecimal total;

	private List<OrderItem> items = new ArrayList<OrderItem>();

	/**
	 * The total is defined as the sum of all {@link OrderItem#getTotal()}.
	 *
	 * @return the total of this {@link Order}.
	 */
	public BigDecimal getTotal() {
		if (total == null) {
			/*
			 * we have to calculate the total and remember the result
			 */
			BigDecimal orderItemTotal = BigDecimal.ZERO;

			List<OrderItem> items = getItems();

			for (OrderItem orderItem : items) {
				BigDecimal itemTotal = orderItem.getTotal();
				/*
				 * add the total of an OrderItem to our total.
				 */
				orderItemTotal = orderItemTotal.add(itemTotal);
			}

			this.total = orderItemTotal;
		}
		return total;
	}

	/**
	 * Adds the {@link OrderItem} to this {@link Order}.
	 *
	 * @param orderItem
	 *            the {@link OrderItem} to add. Must not be null.
	 */
	public void addItem(OrderItem orderItem) {
		if (orderItem == null) {
			throw new IllegalArgumentException("orderItem must not be null");
		}
		if (this.items.add(orderItem)) {
			/*
			 * the list of order items changed so we reset the total field to
			 * let getTotal re-calculate the total.
			 */
			this.total = null;
		}
	}

	/**
	 *
	 * @return the {@link OrderItem} that belong to this {@link Order}. Clients
	 *         may not modify the returned {@link List}. Use
	 *         {@link #addItem(OrderItem)} instead.
	 */
	public List<OrderItem> getItems() {
		/*
		 * we wrap our items to prevent clients from manipulating our internal
		 * state.
		 */
		return Collections.unmodifiableList(items);
	}

}
