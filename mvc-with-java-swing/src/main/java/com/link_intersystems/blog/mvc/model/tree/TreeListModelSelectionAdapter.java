package com.link_intersystems.blog.mvc.model.tree;

import java.util.Observable;
import java.util.Observer;

import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultTreeSelectionModel;
import javax.swing.tree.TreePath;
import javax.swing.tree.TreeSelectionModel;

import com.link_intersystems.blog.mvc.data.Person;
import com.link_intersystems.blog.mvc.model.list.ListModelSelection;

public class TreeListModelSelectionAdapter extends DefaultTreeSelectionModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4155180698317178080L;
	private PersonTreeModel personTreeModel;

	private ListModelSelectionAdapter listModelSelectionAdapter = new ListModelSelectionAdapter();
	private TreeSelectionAdapter treeSelectionAdapter = new TreeSelectionAdapter();

	private ListModelSelection<Person> listModelSelection = new ListModelSelection<Person>();

	public TreeListModelSelectionAdapter(PersonTreeModel personTreeModel) {
		setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
		this.personTreeModel = personTreeModel;
		listModelSelection.addObserver(listModelSelectionAdapter);
		addTreeSelectionListener(treeSelectionAdapter);
	}

	public void setListModelSelection(
			ListModelSelection<Person> listModelSelection) {
		if (this.listModelSelection != null) {
			listModelSelection.deleteObserver(listModelSelectionAdapter);
		}
		this.listModelSelection = listModelSelection;
		if (listModelSelection != null) {
			listModelSelection.addObserver(listModelSelectionAdapter);
		}
	}

	private class TreeSelectionAdapter implements TreeSelectionListener {

		public void valueChanged(TreeSelectionEvent e) {
			TreePath path = e.getPath();
			Person person = personTreeModel.getPerson(path);
			listModelSelection.setSelection(person);

		}

	}

	private class ListModelSelectionAdapter implements Observer {

		public void update(Observable o, Object arg) {
			Person selection = listModelSelection.getSelection();
			if (selection == null) {
				return;
			}
			TreePath treePath = personTreeModel.getTreePath(selection);
			TreePath selectionPath = getSelectionPath();
			if (treePath != null && treePath.isDescendant(selectionPath)) {
				return;
			}
			setSelectionPath(treePath);
		}

	}
}
