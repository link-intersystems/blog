package com.link_intersystems.swing;

import java.awt.event.ActionEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.AbstractAction;
import javax.swing.SwingWorker.StateValue;

import com.link_intersystems.progress.Progress;
import com.link_intersystems.progress.ProgressAware;

public class ProgressCancelAction extends AbstractAction implements ProgressAware {

	private static final long serialVersionUID = 2492163294444587898L;

	private class ProgressListenerAdapter implements PropertyChangeListener {

		@Override
		public void propertyChange(PropertyChangeEvent evt) {
			String propertyName = evt.getPropertyName();

			if ("state".equals(propertyName)) {
				StateValue state = (StateValue) evt.getNewValue();
				switch (state) {
				case PENDING:
				case STARTED:
					progressStarted();
					break;

				default:
					break;
				}
				if (StateValue.DONE.equals(state)) {
					progressDone();
				}
			}
		}
	}

	private ProgressListenerAdapter progressListenerAdapter = new ProgressListenerAdapter();
	private Progress<?> progress;
	private boolean mayInterruptIfRunning = true;

	public ProgressCancelAction() {
		setEnabled(false);
	}

	public void setProgress(Progress<?> progress) {
		this.progress = progress;
		progress.addPropertyChangeListener(progressListenerAdapter);
		setEnabled(progress != null);
	}

	public void setMayInterruptIfRunning(boolean mayInterruptIfRunning) {
		this.mayInterruptIfRunning = mayInterruptIfRunning;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		progress.cancel(mayInterruptIfRunning);
	}

	private void progressDone() {
		progress.removePropertyChangeListener(progressListenerAdapter);
		setEnabled(false);
	}

	private void progressStarted() {
		setEnabled(true);
	}

}