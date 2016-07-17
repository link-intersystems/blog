package com.link_intersystems.blog.mvc.model.tree;

import java.awt.Component;

import javax.swing.JTree;
import javax.swing.tree.DefaultTreeCellRenderer;

import com.link_intersystems.blog.mvc.data.Address;
import com.link_intersystems.blog.mvc.data.Person;
import com.link_intersystems.blog.mvc.model.tree.AddressTreeNode.AddressProperty;

public class PersonTreeCellRenderer extends DefaultTreeCellRenderer {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1515795958576231663L;

	public Component getTreeCellRendererComponent(JTree tree, Object value, boolean selected, boolean expanded,
	        boolean leaf, int row, boolean hasFocus) {
		super.getTreeCellRendererComponent(tree, value, selected, expanded, leaf, row, hasFocus);

		if (value instanceof PersonTreeNode) {
			PersonTreeNode personTreeNode = (PersonTreeNode) value;
			renderPersonNode(personTreeNode);
		} else if (value instanceof AddressTreeNode) {
			AddressTreeNode addressTreeNode = (AddressTreeNode) value;
			Address address = addressTreeNode.getAddress();
			AddressProperty addressProperty = addressTreeNode.getAddressProperty();
			renderAddressNode(address, addressProperty);
		}

		return this;
	}

	private void renderPersonNode(PersonTreeNode personTreeNode) {
		Person person = personTreeNode.getPerson();
		String firstname = person.getFirstname();
		String lastname = person.getLastname();
		String label = firstname + ", " + lastname;
		setText(label);
	}

	private void renderAddressNode(Address address, AddressProperty addressProperty) {
		String label = null;
		switch (addressProperty) {
		case STREET:
			String street = address.getStreet();
			label = "street: " + street;
			break;
		case STREET_NR:
			String streetNr = address.getStreetNr();
			label = "street nr: " + streetNr;
			break;
		case ZIP_CODE:
			String zipCode = address.getZipCode();
			label = "zip code: " + zipCode;
			break;
		case CITY:
			String city = address.getCity();
			label = "city: " + city;
			break;
		}
		setText(label);
	}

}
