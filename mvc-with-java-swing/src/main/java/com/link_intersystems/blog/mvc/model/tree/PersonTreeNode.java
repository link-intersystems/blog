package com.link_intersystems.blog.mvc.model.tree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;

import javax.swing.tree.TreeNode;

import com.link_intersystems.blog.mvc.data.Address;
import com.link_intersystems.blog.mvc.data.Person;
import com.link_intersystems.blog.mvc.model.tree.AddressTreeNode.AddressProperty;

class PersonTreeNode implements TreeNode {

	private Person person;
	private TreeNode parent;

	private List<TreeNode> addressTreeNodes;

	public PersonTreeNode(TreeNode parent, Person person) {
		this.parent = parent;
		this.person = person;
	}

	Person getPerson() {
		return person;
	}

	private List<TreeNode> getAddressTreeNodes() {
		if (addressTreeNodes == null) {
			addressTreeNodes = new ArrayList<TreeNode>();
			Address address = person.getAddress();

			addressTreeNodes.add(new AddressTreeNode(this, address,
					AddressProperty.STREET));
			addressTreeNodes.add(new AddressTreeNode(this, address,
					AddressProperty.STREET_NR));
			addressTreeNodes.add(new AddressTreeNode(this, address,
					AddressProperty.ZIP_CODE));
			addressTreeNodes.add(new AddressTreeNode(this, address,
					AddressProperty.CITY));
		}
		return addressTreeNodes;
	}

	public TreeNode getChildAt(int childIndex) {
		List<TreeNode> addressTreeNodes = getAddressTreeNodes();
		return addressTreeNodes.get(childIndex);
	}

	public int getChildCount() {
		return AddressProperty.values().length;
	}

	public TreeNode getParent() {
		return parent;
	}

	public int getIndex(TreeNode node) {
		List<TreeNode> addressTreeNodes = getAddressTreeNodes();
		return addressTreeNodes.indexOf(node);
	}

	public boolean getAllowsChildren() {
		return true;
	}

	public boolean isLeaf() {
		return false;
	}

	public Enumeration<TreeNode> children() {
		List<TreeNode> addressTreeNodes = getAddressTreeNodes();
		return Collections.enumeration(addressTreeNodes);
	}

}