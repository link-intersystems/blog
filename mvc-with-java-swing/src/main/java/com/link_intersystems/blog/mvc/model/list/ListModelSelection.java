package com.link_intersystems.blog.mvc.model.list;

import java.util.Observable;

import javax.swing.DefaultListSelectionModel;
import javax.swing.ListModel;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 * A {@link ListModelSelection} represents the selected element of an
 * {@link ListModel}. It brings a {@link ListModel} and
 * {@link ListSelectionModel} together.
 *
 * @author René Link <a
 *         href="mailto:rene.link@link-intersystems.com">[rene.link@link-
 *         intersystems.com]</a>
 *
 * @param <E>
 */
public class ListModelSelection<E> extends Observable {

	public ListAdapterListModel<E> listAdapterListModel = new ListAdapterListModel<E>();

	private ListSelectionModel listSelectionModel = new DefaultListSelectionModel();

	private ListSelectionAdapter listSelectionAdapter = new ListSelectionAdapter();

	public ListModelSelection() {
		listSelectionModel.addListSelectionListener(listSelectionAdapter);
	}

	public void setListModels(ListAdapterListModel<E> listAdapterListModel,
			ListSelectionModel listSelectionModel) {
		this.listAdapterListModel = listAdapterListModel;
		if (this.listSelectionModel != null) {
			this.listSelectionModel
					.removeListSelectionListener(listSelectionAdapter);
		}
		this.listSelectionModel = listSelectionModel;
		if (listSelectionModel != null) {
			listSelectionModel.addListSelectionListener(listSelectionAdapter);
		}
	}

	public E getSelection() {
		int minSelectionIndex = listSelectionModel.getMinSelectionIndex();
		E elementAt = null;
		if (minSelectionIndex > -1) {
			elementAt = listAdapterListModel.getElementAt(minSelectionIndex);
		}
		return elementAt;
	}

	private class ListSelectionAdapter implements ListSelectionListener {

		public void valueChanged(ListSelectionEvent e) {
			setChanged();
			notifyObservers();
		}

	}

	public void setSelection(E selection) {
		int indexOf = listAdapterListModel.indexOf(selection);
		listSelectionModel.setSelectionInterval(indexOf, indexOf);
	}
}
