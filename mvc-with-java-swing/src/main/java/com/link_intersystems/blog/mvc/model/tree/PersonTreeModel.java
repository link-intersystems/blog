package com.link_intersystems.blog.mvc.model.tree;

import java.util.ArrayList;
import java.util.List;

import javax.swing.event.ListDataEvent;
import javax.swing.event.ListDataListener;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;

import com.link_intersystems.blog.mvc.data.Person;
import com.link_intersystems.blog.mvc.model.list.ListAdapterListModel;

public class PersonTreeModel extends DefaultTreeModel {

	private static final long serialVersionUID = 1572938192116000199L;

	private ListModelChangeAdapter listModelChangeAdapter = new ListModelChangeAdapter();

	private ListAdapterListModel<Person> personListModel = new ListAdapterListModel<Person>();

	private PersonsRootTreeNode personsRootTreeNode;

	public PersonTreeModel() {
		super(null);
	}

	public void setListModel(ListAdapterListModel<Person> personListModel) {
		if (this.personListModel != null) {
			this.personListModel.removeListDataListener(listModelChangeAdapter);
		}
		this.personListModel = personListModel;
		if (personListModel != null) {
			personListModel.addListDataListener(listModelChangeAdapter);
		}
		personsRootTreeNode = new PersonsRootTreeNode(personListModel);
		setRoot(personsRootTreeNode);
	}

	public TreePath getTreePath(Person person) {
		if (personsRootTreeNode == null) {
			return null;
		}
		List<Object> nodes = new ArrayList<Object>();
		nodes.add(personsRootTreeNode);

		TreeNode treeNode = personsRootTreeNode.getPersonTreeNode(person);
		if (treeNode != null) {
			nodes.add(treeNode);
		}

		Object[] path = (Object[]) nodes.toArray(new Object[nodes.size()]);
		TreePath treePath = new TreePath(path);
		return treePath;
	}

	public Person getPerson(TreePath path) {
		Object[] objectPath = path.getPath();
		Person person = null;
		for (Object object : objectPath) {
			if (object instanceof PersonTreeNode) {
				PersonTreeNode personTreeNode = (PersonTreeNode) object;
				person = personTreeNode.getPerson();
				break;
			}
		}
		return person;
	}

	private class ListModelChangeAdapter implements ListDataListener {

		public void update() {
			personsRootTreeNode.update();
			setRoot(personsRootTreeNode);
		}

		public void intervalAdded(ListDataEvent e) {
			update();
		}

		public void intervalRemoved(ListDataEvent e) {
			update();
		}

		public void contentsChanged(ListDataEvent e) {
			update();
		}

	}

}
