package com.link_intersystems.publications.blog.mvc.controller;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.KeyStroke;

import com.link_intersystems.publications.blog.mvc.model.list.ListAdapterListModel;

public class ClearListModelAction extends AbstractAction {

	/**
	 *
	 */
	private static final long serialVersionUID = 2914235274602131249L;
	private ListAdapterListModel<?> listModel;

	public ClearListModelAction(ListAdapterListModel<?> listModel) {
		this.listModel = listModel;
	}

	@Override
	public Object[] getKeys() {
		return new Object[] { Action.NAME };
	}

	@Override
	public Object getValue(String key) {
		if (Action.NAME.equals(key)) {
			return "Clear";
		} else if (Action.ACCELERATOR_KEY.equals(key)) {
			return KeyStroke.getKeyStroke('C');
		}
		return super.getValue(key);
	}

	public void actionPerformed(ActionEvent e) {
		listModel.clear();
	}

}
