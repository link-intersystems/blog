package com.link_intersystems.publications.blog.model.anemic;

import java.math.BigDecimal;
import java.util.List;

public class OrderService {

	public void calculateTotal(Order order) {
		if (order == null) {
			throw new IllegalArgumentException("order must not be null");
		}

		BigDecimal total = BigDecimal.ZERO;
		List<OrderItem> items = order.getItems();

		for (OrderItem orderItem : items) {
			int quantity = orderItem.getQuantity();
			BigDecimal price = orderItem.getPrice();
			BigDecimal itemTotal = price.multiply(new BigDecimal(quantity));
			total = total.add(itemTotal);
		}
		order.setTotal(total);
	}
}
