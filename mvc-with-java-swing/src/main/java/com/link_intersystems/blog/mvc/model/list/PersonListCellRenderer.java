package com.link_intersystems.blog.mvc.model.list;

import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

import com.link_intersystems.blog.mvc.data.Address;
import com.link_intersystems.blog.mvc.data.Person;

/**
 * A {@link ListCellRenderer} that uses an {@link ObjectToString} to get a
 * string representation for the model objects it renders.
 *
 * @author René Link <a
 *         href="mailto:rene.link@link-intersystems.com">[rene.link@link-
 *         intersystems.com]</a>
 *
 * @param <T>
 */
public class PersonListCellRenderer extends JLabel implements
		ListCellRenderer<Person> {

	/**
	 *
	 */
	private static final long serialVersionUID = -1614367901813214864L;

	@Override
	public Component getListCellRendererComponent(JList<? extends Person> list,
			Person value, int index, boolean isSelected, boolean cellHasFocus) {
		String toString = toString(value);
		setText(toString);
		return this;
	}

	private String toString(Person person) {
		if (person == null) {
			return "";
		}
		StringBuilder personToStringBuilder = new StringBuilder();

		personToStringBuilder.append(person.getFirstname());
		personToStringBuilder.append(", ");
		personToStringBuilder.append(person.getLastname());
		Address address = person.getAddress();
		if (address != null) {
			personToStringBuilder.append(", ");
			personToStringBuilder.append(address.getStreet());
			personToStringBuilder.append(", ");
			personToStringBuilder.append(address.getStreetNr());
			personToStringBuilder.append(", ");
			personToStringBuilder.append(address.getZipCode());
			personToStringBuilder.append(", ");
			personToStringBuilder.append(address.getCity());
		}
		return personToStringBuilder.toString();
	}

}
