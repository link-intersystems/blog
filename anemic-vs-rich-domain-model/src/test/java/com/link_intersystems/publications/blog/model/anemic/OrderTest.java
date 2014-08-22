package com.link_intersystems.publications.blog.model.anemic;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;

import java.math.BigDecimal;

import org.junit.Test;

import com.link_intersystems.publications.blog.model.anemic.Order;
import com.link_intersystems.publications.blog.model.anemic.OrderItem;
import com.link_intersystems.publications.blog.model.anemic.OrderService;

public class OrderTest {

	/**
	 * This test shows that an anemic model can be in an inconsistent state,
	 * because it doesn't handle it's state changes. So an anemic model can
	 * never guarantee that it is in a legal state. State handling of an anemic
	 * model is placed outside that object in classes mostly named "Service",
	 * "Helper", "Util", "Manager" etc.
	 * <p>
	 * <blockquote> The problem with an anemic model is that a client must know
	 * in which state the object it uses is and which service methods it has to
	 * call if it changes the state of an anemic model to keep the object in a
	 * legal state. </blockquote>
	 * </p>
	 */
	@Test
	public void anAnemicModelCanBeInconsistent() {
		OrderService orderService = new OrderService();
		Order order = new Order();
		BigDecimal total = order.getTotal();

		/*
		 * A new order has no items and therefore the total must be zero.
		 */
		assertEquals(BigDecimal.ZERO, total);

		OrderItem aGoodBook = new OrderItem();
		aGoodBook.setName("Domain-Driven");
		aGoodBook.setPrice(new BigDecimal("30"));
		aGoodBook.setQuantity(5);

		/*
		 * We break encapsulation here as we alter the internal state of the
		 * order's item list. This is a common programming pattern when using
		 * anemic models.
		 */
		order.getItems().add(aGoodBook);

		/*
		 * After we added an OrderItem. The Order object is in an illegal state.
		 */
		BigDecimal totalAfterItemAdd = order.getTotal();
		BigDecimal expectedTotal = new BigDecimal("150");

		boolean isExpectedTotal = expectedTotal.equals(totalAfterItemAdd);

		/*
		 * Of course the order's total can not be the expected total, because
		 * anemic models do not handle their state changes.
		 */
		assertFalse(isExpectedTotal);

		/*
		 * To fix it we have to call the OrderService to re-calculate the total
		 * and bring the Order object to a legal state again.
		 */
		orderService.calculateTotal(order);

		/*
		 * Now the order is in a legal state again.
		 */
		BigDecimal totalAfterRecalculation = order.getTotal();
		assertEquals(expectedTotal, totalAfterRecalculation);
	}
}
