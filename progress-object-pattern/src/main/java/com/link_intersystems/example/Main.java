package com.link_intersystems.example;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.util.concurrent.TimeUnit;

import javax.swing.Action;
import javax.swing.BoundedRangeModel;
import javax.swing.DefaultBoundedRangeModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.text.Document;
import javax.swing.text.PlainDocument;

import com.link_intersystems.swing.BoundedRangeProgress;
import com.link_intersystems.swing.ComponentVisibility;
import com.link_intersystems.swing.ProgressCancelAction;

public class Main {

	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {

			@Override
			public void run() {
				Main progressExampleApp = new Main();
				progressExampleApp.createAndShowUI();
			}
		});

	}

	public void createAndShowUI() {
		BoundedRangeModel progressModel = new DefaultBoundedRangeModel();
		BoundedRangeProgress progressAdapter = new BoundedRangeProgress(progressModel);
		Document resultDocument = new PlainDocument();

		ComponentVisibility progressBarVisibility = new ComponentVisibility("enabled", false);
		progressBarVisibility.setInvisibleDelay(1, TimeUnit.SECONDS);

		ProgressCancelAction cancelAction = new ProgressCancelAction();
		cancelAction.putValue(Action.NAME, "Cancel");

		ProgressSimulationAction progressAction = new ProgressSimulationAction();
		progressAction.addProgressAware(progressAdapter);
		progressAction.addProgressAware(cancelAction);
		progressAction.addPropertyChangeListener(progressBarVisibility);
		progressAction.putValue(Action.NAME, "Start");
		progressAction.setResultDocument(resultDocument);

		JFrame mainFrame = new JFrame("Progress Object Pattern with Java Swing");
		mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		mainFrame.setMinimumSize(new Dimension(600, 120));

		JProgressBar progressBar = new JProgressBar(progressModel);
		progressBar.setVisible(false);
		progressBar.setStringPainted(true);
		progressBarVisibility.setComponent(progressBar);

		JButton startProgressButton = new JButton(progressAction);
		JButton cancelButton = new JButton(cancelAction);
		JTextField resultTextField = new JTextField(40);
		resultTextField.setEditable(false);
		resultTextField.setDocument(resultDocument);

		JPanel mainPanel = new JPanel();
		mainPanel.add(startProgressButton);
		mainPanel.add(cancelButton);
		mainPanel.add(resultTextField);

		Container contentPane = mainFrame.getContentPane();
		contentPane.add(mainPanel);
		contentPane.add(progressBar, BorderLayout.SOUTH);

		mainFrame.pack();
		mainFrame.setLocationRelativeTo(null);
		mainFrame.setVisible(true);
	}
}
