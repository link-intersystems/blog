package com.link_intersystems.swing;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.SwingWorker;
import javax.swing.SwingWorker.StateValue;

public class SwingWorkerPropertyChangeAdapter implements PropertyChangeListener {

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		String propertyName = evt.getPropertyName();
		if ("state".equals(propertyName)) {
			StateValue state = (StateValue) evt.getNewValue();
			switch (state) {
			case PENDING:
			case STARTED:
				started();
				break;
			case DONE:
				SwingWorker<?, ?> future = (SwingWorker<?, ?>) evt.getSource();
				boolean cancelled = future.isCancelled();
				done(cancelled);
			}
		}
	}

	protected void started() {
	}

	protected void done(boolean cancelled) {
	}

}
