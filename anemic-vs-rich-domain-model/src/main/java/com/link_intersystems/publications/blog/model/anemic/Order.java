package com.link_intersystems.publications.blog.model.anemic;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Order {

	private BigDecimal total = BigDecimal.ZERO;

	private List<OrderItem> items = new ArrayList<OrderItem>();

	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

	public List<OrderItem> getItems() {
		return items;
	}

	public void setItems(List<OrderItem> items) {
		this.items = items;
	}

}
