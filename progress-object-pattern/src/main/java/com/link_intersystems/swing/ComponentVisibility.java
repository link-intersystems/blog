package com.link_intersystems.swing;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.swing.JLabel;
import javax.swing.SwingWorker;
import javax.swing.Timer;
import javax.swing.SwingWorker.StateValue;

/**
 * A {@link ComponentVisibility} is a {@link PropertyChangeListener} that
 * controls the visibility of a {@link Component} depending on property values.
 * As long as a property has a defined value the {@link ComponentVisibility} set
 * the controlled {@link Component} visible, otherwise invisible. E.g.
 * 
 * <pre>
 * {@link SwingWorker} sw = ...;
 * 
 * {@link ComponentVisibility} visibleWhileRunning = new {@link ComponentVisibility}("state", {@link StateValue#STARTED}, {@link StateValue#PENDING});
 * 
 * {@link JLabel} workerStateLabel = new {@link JLabel}("Worker is running");
 * visibleWhileRunning.setComponent(workerStateLabel)
 * 
 * </pre>
 * 
 * @author <a href="mailto:rene.link@link-intersystems.com">René Link</a>
 *
 */
public class ComponentVisibility implements PropertyChangeListener {

	private static class VisibilityDelay implements ActionListener {

		private Component component;
		private boolean visible;

		public VisibilityDelay(Component component, boolean visible) {
			this.component = component;
			this.visible = visible;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			component.setVisible(visible);
		}

	}

	private Component component;
	private String visibilityPropertyName;
	private List<Object> visibilityPropertyValues;
	private long invisibleDelay;
	private Timer invisibleTimer;

	public ComponentVisibility(String visibilityPropertyName, Object... visibilityPropertyValues) {
		this.visibilityPropertyName = visibilityPropertyName;
		this.visibilityPropertyValues = Arrays.asList(visibilityPropertyValues);
	}

	public void setComponent(Component component) {
		this.component = component;
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		if (component == null) {
			return;
		}

		String propertyName = evt.getPropertyName();
		if (visibilityPropertyName.equals(propertyName)) {
			if (invisibleTimer != null) {
				invisibleTimer.stop();
			}
			invisibleTimer = null;

			Object newPropertyValue = evt.getNewValue();
			boolean visible = visibilityPropertyValues.contains(newPropertyValue);
			if (visible || invisibleDelay == 0) {
				component.setVisible(visible);

			} else {
				VisibilityDelay visibilityDelay = new VisibilityDelay(component, false);
				invisibleTimer = new Timer((int) invisibleDelay, visibilityDelay);
				invisibleTimer.start();
			}

		}
	}

	public void setInvisibleDelay(int time, TimeUnit unit) {
		this.invisibleDelay = TimeUnit.MILLISECONDS.convert(time, unit);

	}

}
