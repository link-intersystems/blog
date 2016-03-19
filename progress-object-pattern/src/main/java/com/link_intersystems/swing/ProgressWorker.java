package com.link_intersystems.swing;

import javax.swing.SwingWorker;

import com.link_intersystems.progress.Progress;

public abstract class ProgressWorker<T> extends SwingWorker<T, Void> implements Progress<T> {
}
