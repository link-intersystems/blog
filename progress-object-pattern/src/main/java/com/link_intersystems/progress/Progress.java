package com.link_intersystems.progress;

import java.util.concurrent.Future;

/**
 * A {@link Progress} for a value that will be available in the {@link Future}.
 * {@link Progress} adds {@link PropertyChangeSupported} to a {@link Progress}
 * and {@link Future}.
 * <p/>
 * The supported properties are:
 * <ul>
 * <li>progress<br/>
 * An integer value of the progress in percent (0 to 100).</li>
 * <li>value<br/>
 * The value {@link Future#V} if the {@link Future} was not canceled (
 * {@link Future#cancel(boolean)}) otherwise <code>null</code>.</li>
 * </ul>
 * 
 * @author <a href="mailto:rene.link@link-intersystems.com">René Link</a>
 *
 * @param <V>
 */
public interface Progress<V> extends Future<V>, PropertyChangeSupported {

	public int getProgress();

}
