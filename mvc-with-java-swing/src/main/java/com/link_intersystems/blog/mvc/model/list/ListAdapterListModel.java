package com.link_intersystems.blog.mvc.model.list;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.AbstractListModel;
import javax.swing.ListModel;

/**
 * A {@link ListModel} that is based upon a {@link List}.
 *
 * @author René Link <a
 *         href="mailto:rene.link@link-intersystems.com">[rene.link@link-
 *         intersystems.com]</a>
 *
 */
public class ListAdapterListModel<E> extends AbstractListModel<E> {

	/**
	 *
	 */
	private static final long serialVersionUID = -3348705533107354297L;

	private List<E> list = new ArrayList<E>();

	public void setList(List<E> list) {
		this.list.clear();
		this.list.addAll(list);
		fireListDataChanged();
	}

	public void clear() {
		this.list.clear();
		fireListDataChanged();
	}

	private void fireListDataChanged() {
		fireContentsChanged(this, 0, Math.max(list.size() - 1, 0));
	}

	public void addAll(List<E> elements) {
		if (this.list.addAll(elements)) {
			fireIntervalAdded(elements.size());
		}
	}

	protected void fireIntervalAdded(int elementCOunt) {
		int index0 = list.size() - elementCOunt;
		fireIntervalAdded(this, Math.max(0, index0),
				Math.max(0, list.size() - 1));
	}

	/**
	 * Returns the list that this {@link ListAdapterListModel} is backed by.
	 *
	 * @return
	 */
	public List<E> getList() {
		return Collections.unmodifiableList(list);
	}

	public int getSize() {
		return list.size();
	}

	public E getElementAt(int index) {
		E elementAt = list.get(index);
		return elementAt;
	}

	public int indexOf(E element) {
		return list.indexOf(element);
	}

}
