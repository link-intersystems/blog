package com.link_intersystems.progress;

import java.beans.PropertyChangeListener;

public interface PropertyChangeSupported {

	public void addPropertyChangeListener(PropertyChangeListener propertyChangeListener);

	public void removePropertyChangeListener(PropertyChangeListener propertyChangeListener);

}
