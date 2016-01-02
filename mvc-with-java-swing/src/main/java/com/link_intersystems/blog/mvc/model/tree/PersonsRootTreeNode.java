package com.link_intersystems.blog.mvc.model.tree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;

import javax.swing.tree.TreeNode;

import com.link_intersystems.blog.mvc.data.Person;
import com.link_intersystems.blog.mvc.model.list.ListAdapterListModel;

class PersonsRootTreeNode implements TreeNode {

	private List<TreeNode> personTreeNodes;
	private ListAdapterListModel<Person> personListModel;

	public PersonsRootTreeNode(ListAdapterListModel<Person> personListModel) {
		this.personListModel = personListModel;
	}

	private List<TreeNode> getPersonTreeNodes() {
		if (personTreeNodes == null) {
			personTreeNodes = new ArrayList<TreeNode>();

			int size = personListModel.getSize();
			for (int i = 0; i < size; i++) {
				Person person = (Person) personListModel.getElementAt(i);
				PersonTreeNode personTreeNode = new PersonTreeNode(this, person);
				personTreeNodes.add(personTreeNode);
			}
		}
		return personTreeNodes;
	}

	public TreeNode getChildAt(int childIndex) {
		List<TreeNode> personTreeNodes = getPersonTreeNodes();
		return personTreeNodes.get(childIndex);
	}

	public int getChildCount() {
		List<TreeNode> personTreeNodes = getPersonTreeNodes();
		return personTreeNodes.size();
	}

	public TreeNode getParent() {
		return null;
	}

	public int getIndex(TreeNode node) {
		List<TreeNode> personTreeNodes = getPersonTreeNodes();
		return personTreeNodes.indexOf(node);
	}

	public boolean getAllowsChildren() {
		return true;
	}

	public boolean isLeaf() {
		return false;
	}

	public Enumeration<TreeNode> children() {
		List<TreeNode> personTreeNodes = getPersonTreeNodes();
		return Collections.enumeration(personTreeNodes);
	}

	TreeNode getPersonTreeNode(Person person) {
		TreeNode personTreeNode = null;
		int personIndex = personListModel.indexOf(person);
		if (personIndex > -1) {
			List<TreeNode> personTreeNodes = getPersonTreeNodes();
			personTreeNode = personTreeNodes.get(personIndex);
		}
		return personTreeNode;

	}

	void update() {
		this.personTreeNodes = null;
	}
}