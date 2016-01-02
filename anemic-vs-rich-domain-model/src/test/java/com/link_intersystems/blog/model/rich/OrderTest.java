package com.link_intersystems.blog.model.rich;

import static junit.framework.Assert.assertEquals;

import java.math.BigDecimal;
import java.util.List;

import org.junit.Test;

import com.link_intersystems.blog.model.rich.Order;
import com.link_intersystems.blog.model.rich.OrderItem;

public class OrderTest {

	/**
	 * This test shows that a rich model gurantees that it is in a legal state
	 * at any time.
	 */
	@Test
	public void anAnemicModelCanBeInconsistent() {
		Order order = new Order();
		BigDecimal total = order.getTotal();

		/*
		 * A new order has no items and therefore the total must be zero.
		 */
		assertEquals(BigDecimal.ZERO, total);

		OrderItem aGoodBook = new OrderItem(new BigDecimal("30"), 5,
				"Domain-Driven");
		List<OrderItem> items = order.getItems();

		try {
			items.add(aGoodBook);
		} catch (UnsupportedOperationException e) {
			/*
			 * We CAN NOT BREAK ENCAPSULATION, because the order object will not
			 * expose it's internal state to clients. It takes care about it's
			 * state and ensures that it is in a legal state at any time.
			 */
		}

		/*
		 * We have to use the object's mutator method
		 */
		order.addItem(aGoodBook);

		/*
		 * After we added an OrderItem. The object is still in a legal state.
		 */
		BigDecimal totalAfterItemAdd = order.getTotal();
		BigDecimal expectedTotal = new BigDecimal("150");

		assertEquals(expectedTotal, totalAfterItemAdd);
	}
}
