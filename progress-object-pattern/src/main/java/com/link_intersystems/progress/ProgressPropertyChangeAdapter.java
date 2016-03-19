package com.link_intersystems.progress;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class ProgressPropertyChangeAdapter implements PropertyChangeListener {

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		String propertyName = evt.getPropertyName();
		if ("progress".equals(propertyName)) {
			Integer progressPercent = (Integer) evt.getNewValue();
			progress(progressPercent);
		}
	}

	protected void progress(int percent) {
	}

}
