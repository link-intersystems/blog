package com.link_intersystems.blog.mvc;

import com.link_intersystems.blog.mvc.view.MainFrame;

/**
 * Application entry point.
 *
 * @author René Link <a
 *         href="mailto:rene.link@link-intersystems.com">[rene.link@link-
 *         intersystems.com]</a>
 *
 */
public class Main {

	public static void main(String[] args) {
		MainFrame mainFrame = new MainFrame();
		mainFrame.setVisible(true);
	}
}
