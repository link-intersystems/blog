package com.link_intersystems.blog.mvc.view;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JProgressBar;

import com.link_intersystems.blog.mvc.controller.ClearListModelAction;
import com.link_intersystems.blog.mvc.controller.LoadPersonsAction;
import com.link_intersystems.blog.mvc.controller.SwingWorkerProgressModel;
import com.link_intersystems.blog.mvc.data.Person;
import com.link_intersystems.blog.mvc.model.list.ListAdapterListModel;

public class MainFrame extends JFrame {

	/**
	 *
	 */
	private static final long serialVersionUID = 4353611743416911021L;

	private ListAdapterListModel<Person> personListModel = new ListAdapterListModel<Person>();

	private SwingWorkerProgressModel swingWorkerProgressModel = new SwingWorkerProgressModel();
	private JProgressBar progressBar = new JProgressBar(
			swingWorkerProgressModel);

	private SwingWorkerBasedComponentVisibility swingWorkerBasedComponentVisibility = new SwingWorkerBasedComponentVisibility(
			progressBar);

	private OverviewPanel overviewPanel = new OverviewPanel();
	private LoadSpeedSimulationPanel loadSpeedSimulationPanel = new LoadSpeedSimulationPanel();

	private Component currentContent;

	public MainFrame() {
		Container contentPane = getContentPane();
		contentPane.setLayout(new BorderLayout());
		progressBar.setStringPainted(true);
		overviewPanel.setPersonList(personListModel);

		setContent(overviewPanel);
		getContentPane().add(loadSpeedSimulationPanel, BorderLayout.NORTH);
		getContentPane().add(progressBar, BorderLayout.SOUTH);

		JMenuBar jMenuBar = new JMenuBar();
		setJMenuBar(jMenuBar);
		initMenu(jMenuBar);

		setSize(605, 660);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Swing MVC Implementation Example");
		setLocationRelativeTo(null);
	}

	private void initMenu(JMenuBar jMenuBar) {
		initFileMenu(jMenuBar);
	}

	private void initFileMenu(JMenuBar jMenuBar) {
		JMenu fileMenu = new JMenu("Persons");
		jMenuBar.add(fileMenu);

		LoadPersonsAction loadPersonsAction = new LoadPersonsAction(
				personListModel);
		loadPersonsAction
				.addSwingWorkerPropertyChangeListener(swingWorkerProgressModel);
		loadPersonsAction
				.addSwingWorkerPropertyChangeListener(swingWorkerBasedComponentVisibility);
		loadPersonsAction.setLoadSpeedModel(loadSpeedSimulationPanel
				.getPersonsLoadSpeedModel());

		JMenuItem loadMenuItem = new JMenuItem(loadPersonsAction);
		fileMenu.add(loadMenuItem);

		ClearListModelAction clearPersonsModelAction = new ClearListModelAction(
				personListModel);
		JMenuItem clearPersonsMenuItem = new JMenuItem(clearPersonsModelAction);
		fileMenu.add(clearPersonsMenuItem);
	}

	public void setContent(Component component) {
		Container contentPane = getContentPane();
		if (currentContent != null) {
			contentPane.remove(currentContent);
		}
		contentPane.add(component, BorderLayout.CENTER);
		currentContent = component;
		contentPane.doLayout();
		repaint();
	}

}
