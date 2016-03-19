package com.link_intersystems.swing;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.BoundedRangeModel;

import com.link_intersystems.progress.Progress;
import com.link_intersystems.progress.ProgressAware;

/**
 * Connects a {@link BoundedRangeModel} to a {@link Progress} to synchronizes
 * the {@link Progress}'s state with the {@link BoundedRangeModel}.
 * 
 * 
 * @author <a href="mailto:rene.link@link-intersystems.com">René Link</a>
 *
 */
public class BoundedRangeProgress implements ProgressAware {

	private class ProgressPropertyChangeListener implements PropertyChangeListener {

		@Override
		public void propertyChange(PropertyChangeEvent evt) {
			if ("progress".equals(evt.getPropertyName())) {
				updateProgressModel();
			}
		}
	}

	private ProgressPropertyChangeListener progressPropertyChangeListener = new ProgressPropertyChangeListener();

	private BoundedRangeModel progressModel;
	private Progress<?> progress;

	public BoundedRangeProgress(BoundedRangeModel progressModel) {
		this.progressModel = progressModel;
	}

	public void setProgress(Progress<?> progress) {
		if (this.progress != null) {
			this.progress.removePropertyChangeListener(progressPropertyChangeListener);
		}

		this.progress = progress;

		if (this.progress != null) {
			initializeProgressModel();
			this.progress.addPropertyChangeListener(progressPropertyChangeListener);

		}
	}

	private void initializeProgressModel() {
		progressModel.setValueIsAdjusting(true);
		progressModel.setMinimum(0);
		progressModel.setMaximum(100);
		updateProgressModel();
		progressModel.setValueIsAdjusting(false);
	}

	private void updateProgressModel() {
		progressModel.setValue(progress.getProgress());
	}
}
