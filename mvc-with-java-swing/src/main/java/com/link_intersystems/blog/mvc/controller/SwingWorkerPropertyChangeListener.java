package com.link_intersystems.blog.mvc.controller;

import java.beans.PropertyChangeListener;

import javax.swing.BoundedRangeModel;
import javax.swing.SwingWorker;

/**
 * An interface that insures that a {@link PropertyChangeListener} that must be
 * attached to a {@link SwingWorker} can only be attached to a
 * {@link SwingWorker}. This interface therefore provides type-safety for a
 * {@link PropertyChangeListener}.
 * 
 * @author René Link <a
 *         href="mailto:rene.link@link-intersystems.com">[rene.link@link-
 *         intersystems.com]</a>
 * 
 */
public interface SwingWorkerPropertyChangeListener {

	/**
	 * Attaches this {@link SwingWorkerProgressModel} as an
	 * {@link PropertyChangeListener} to the given {@link SwingWorker} so that
	 * the {@link SwingWorker}'s progress events will update this
	 * {@link BoundedRangeModel}.
	 * 
	 * @param sw
	 *            the {@link SwingWorker} to attach this {@link SwingWorker}'s
	 *            {@link PropertyChangeListener} adater from.
	 */
	public abstract void attachPropertyChangeListener(SwingWorker<?, ?> sw);

	/**
	 * Detaches this {@link SwingWorkerProgressModel}'s
	 * {@link PropertyChangeListener} adapter from the given {@link SwingWorker}
	 * so that no progress events will update this {@link BoundedRangeModel}
	 * anymore.
	 * 
	 * @param sw
	 *            the {@link SwingWorker} to dettach this {@link SwingWorker}'s
	 *            {@link PropertyChangeListener} adater from.
	 */
	public abstract void detachPropertyChangeListener(SwingWorker<?, ?> sw);

}