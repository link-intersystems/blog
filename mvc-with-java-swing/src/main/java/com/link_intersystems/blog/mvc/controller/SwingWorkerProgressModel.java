package com.link_intersystems.blog.mvc.controller;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.BoundedRangeModel;
import javax.swing.DefaultBoundedRangeModel;
import javax.swing.SwingWorker;

/**
 * Adapts the progress of a {@link SwingWorker} to a {@link BoundedRangeModel}.
 * A {@link SwingWorkerProgressModel} is a {@link PropertyChangeListener} that
 * must be attached to a {@link SwingWorker}, received progress updated events
 * from the {@link SwingWorker} and updates the {@link BoundedRangeModel} that
 * it is.
 *
 * @author René Link <a
 *         href="mailto:rene.link@link-intersystems.com">[rene.link@link-
 *         intersystems.com]</a>
 *
 */
public class SwingWorkerProgressModel extends DefaultBoundedRangeModel
		implements SwingWorkerPropertyChangeListener {

	/**
	 *
	 */
	private static final long serialVersionUID = 2652024572507645835L;

	private PropertyChangeListener swingWorkerAdapter = new SwingWorkerPropertyChangeListenerAdapter();

	/**
	 * @inherited
	 */
	@Override
	public void attachPropertyChangeListener(SwingWorker<?, ?> swingWorker) {
		swingWorker.addPropertyChangeListener(swingWorkerAdapter);
	}

	/**
	 * @inherited
	 */
	@Override
	public void detachPropertyChangeListener(SwingWorker<?, ?> swingWorker) {
		swingWorker.removePropertyChangeListener(swingWorkerAdapter);
	}

	private class SwingWorkerPropertyChangeListenerAdapter implements
			PropertyChangeListener {

		public void propertyChange(PropertyChangeEvent evt) {
			SwingWorker<?, ?> swingWorker = (SwingWorker<?, ?>) evt.getSource();
			setValueIsAdjusting(true);
			setMinimum(0);
			setMaximum(100);
			setValue(swingWorker.getProgress());
			setValueIsAdjusting(false);
		}

	}

}
