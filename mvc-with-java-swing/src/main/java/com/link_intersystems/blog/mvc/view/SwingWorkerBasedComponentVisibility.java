package com.link_intersystems.blog.mvc.view;

import java.awt.Component;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.SwingWorker;
import javax.swing.SwingWorker.StateValue;

import com.link_intersystems.blog.mvc.controller.SwingWorkerPropertyChangeListener;

/**
 * Controls the visibility of a set of components depending on a
 * {@link SwingWorker}'s {@link StateValue} by listening to the
 * {@link SwingWorker}'s {@link PropertyChangeEvent}.
 * 
 * @author René Link <a
 *         href="mailto:rene.link@link-intersystems.com">[rene.link@link-
 *         intersystems.com]</a>
 * 
 */
public class SwingWorkerBasedComponentVisibility implements
		SwingWorkerPropertyChangeListener {

	private SwingWorkerPropertyChangeAdapter swingWorkerPropertyChangeAdapter = new SwingWorkerPropertyChangeAdapter();

	private Component[] visibleComponentsOnProgress;

	public SwingWorkerBasedComponentVisibility(
			Component... visibleComponentsOnProgress) {
		this.visibleComponentsOnProgress = visibleComponentsOnProgress;
		setComponentsVisible(false);
	}

	private void setComponentsVisible(boolean visibility) {
		for (Component component : visibleComponentsOnProgress) {
			component.setVisible(visibility);
		}
	}

	public void attachPropertyChangeListener(SwingWorker<?, ?> swingWorker) {
		swingWorker.addPropertyChangeListener(swingWorkerPropertyChangeAdapter);
	}

	public void detachPropertyChangeListener(SwingWorker<?, ?> swingWorker) {
		swingWorker
				.removePropertyChangeListener(swingWorkerPropertyChangeAdapter);

	}

	private class SwingWorkerPropertyChangeAdapter implements
			PropertyChangeListener {

		public void propertyChange(PropertyChangeEvent evt) {
			String propertyName = evt.getPropertyName();
			if ("state".equals(propertyName)) {
				StateValue stateValue = (StateValue) evt.getNewValue();
				switch (stateValue) {
				case STARTED:
				case PENDING:
					setComponentsVisible(true);
					break;
				case DONE:
					setComponentsVisible(false);
					break;
				}
			}
		}

	}

}
