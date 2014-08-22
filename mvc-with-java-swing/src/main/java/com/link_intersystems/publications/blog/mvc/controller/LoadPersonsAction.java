package com.link_intersystems.publications.blog.mvc.controller;

import java.awt.event.ActionEvent;
import java.util.Collection;
import java.util.HashSet;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.BoundedRangeModel;
import javax.swing.DefaultBoundedRangeModel;
import javax.swing.KeyStroke;

import com.link_intersystems.publications.blog.mvc.data.Person;
import com.link_intersystems.publications.blog.mvc.model.list.ListAdapterListModel;

/**
 * 
 * @author rene.link
 *
 */
public class LoadPersonsAction extends AbstractAction {

	/**
	 *
	 */
	private static final long serialVersionUID = 2636985714796751517L;
	private ListAdapterListModel<Person> personListModel;
	private Collection<SwingWorkerPropertyChangeListener> swingWorkerPropertyChangeListeners = new HashSet<SwingWorkerPropertyChangeListener>();
	private BoundedRangeModel loadPersonsSpeedModel = new DefaultBoundedRangeModel();

	public LoadPersonsAction(ListAdapterListModel<Person> personListModel) {
		this.personListModel = personListModel;
	}

	@Override
	public Object[] getKeys() {
		return new Object[] { Action.NAME };
	}

	@Override
	public Object getValue(String key) {
		if (Action.NAME.equals(key)) {
			return "Load";
		} else if (Action.ACCELERATOR_KEY.equals(key)) {
			return KeyStroke.getKeyStroke('L');
		}
		return super.getValue(key);
	}

	public void actionPerformed(ActionEvent e) {
		LoadPersonsWorker loadPersonsWorker = new LoadPersonsWorker(
				personListModel);
		loadPersonsWorker.setLoadSpeedModel(loadPersonsSpeedModel);
		for (SwingWorkerPropertyChangeListener swingWorkerPropertyChangeListener : swingWorkerPropertyChangeListeners) {
			swingWorkerPropertyChangeListener
					.attachPropertyChangeListener(loadPersonsWorker);
		}
		loadPersonsWorker.execute();
	}

	public void addSwingWorkerPropertyChangeListener(
			SwingWorkerPropertyChangeListener swingWorkerPropertyChangeListener) {
		swingWorkerPropertyChangeListeners
				.add(swingWorkerPropertyChangeListener);
	}

	public void removeSwingWorkerPropertyChangeListener(
			SwingWorkerPropertyChangeListener swingWorkerPropertyChangeListener) {
		swingWorkerPropertyChangeListeners
				.remove(swingWorkerPropertyChangeListener);
	}

	public void setLoadSpeedModel(BoundedRangeModel loadPersonsSpeedModel) {
		this.loadPersonsSpeedModel = loadPersonsSpeedModel;
	}
}
