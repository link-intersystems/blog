package com.link_intersystems.blog.mvc.controller;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.KeyStroke;

import com.link_intersystems.blog.mvc.model.list.ListAdapterListModel;

public class ClearListModelAction extends AbstractAction {

	private static final long serialVersionUID = 2914235274602131249L;

	private ListAdapterListModel<?> listModel;

	public ClearListModelAction(ListAdapterListModel<?> listModel) {
		this.listModel = listModel;

		putValue(NAME, "Clear");
		putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke('C'));
	}

	public void actionPerformed(ActionEvent e) {
		listModel.clear();
	}

}
