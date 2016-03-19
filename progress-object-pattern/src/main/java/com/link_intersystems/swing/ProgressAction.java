package com.link_intersystems.swing;

import java.awt.event.ActionEvent;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.concurrent.Future;

import javax.swing.AbstractAction;
import javax.swing.Action;

import com.link_intersystems.progress.Progress;
import com.link_intersystems.progress.ProgressAware;

/**
 * A {@link ProgressAction} is an {@link Action} that executes a
 * {@link ProgressWorker3} on {@link Action#actionPerformed(ActionEvent)}. It
 * also disables itself as long as the {@link ProgressWorker3} is running and
 * enables itself when the {@link ProgressWorker3} is done.
 * 
 * <p/>
 * Subclasses must override the {@link #createProgressWorker()} factory method.
 * 
 * @author <a href="mailto:rene.link@link-intersystems.com">René Link</a>
 * 
 *
 * @param <T>
 */
public abstract class ProgressAction<T> extends AbstractAction {

	private static final long serialVersionUID = 2088582900434640623L;

	private class SwingWorkerPropertyAdapter extends SwingWorkerPropertyChangeAdapter {

		@Override
		protected void done(boolean cancelled) {
			ProgressAction<?> progressAction = ProgressAction.this;
			progressAction.workerDone();
		}
	}

	private SwingWorkerPropertyAdapter swingWorkerPropertyAdapter = new SwingWorkerPropertyAdapter();
	private Collection<ProgressAware> progressAwares = new LinkedHashSet<ProgressAware>();

	private Progress<T> progress;

	public void addProgressAware(ProgressAware progressAware) {
		this.progressAwares.add(progressAware);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		setEnabled(false);

		ProgressWorker<T> progressWorker = createProgressWorker();
		setProgress(progressWorker);
		progressWorker.execute();
	}

	protected abstract ProgressWorker<T> createProgressWorker();

	private void setProgress(Progress<T> progress) {
		this.progress = progress;

		for (ProgressAware progressAware : progressAwares) {
			progressAware.setProgress(progress);
		}

		progress.addPropertyChangeListener(swingWorkerPropertyAdapter);
	}

	void workerDone() {
		progress.removePropertyChangeListener(swingWorkerPropertyAdapter);
		try {
			done(progress);
		} finally {
			progress = null;
			setEnabled(true);
		}
	}

	protected void done(Future<T> result) {
	}

}
