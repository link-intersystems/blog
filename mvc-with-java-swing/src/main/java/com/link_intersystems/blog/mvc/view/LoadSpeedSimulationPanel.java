package com.link_intersystems.blog.mvc.view;

import java.awt.Dimension;
import java.awt.Font;
import java.text.MessageFormat;

import javax.swing.BoundedRangeModel;
import javax.swing.DefaultBoundedRangeModel;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.text.Document;

import com.link_intersystems.blog.mvc.model.BoundedRangeModel2DocumentAdapter;

public class LoadSpeedSimulationPanel extends JPanel {

	private static final int MIN_LOAD_SPEED_TICK_MS = 250;
	private static final int MAX_LOAD_SPEED_MS = 5000;
	private static final int INITIAL_LOAD_SPEED_MS = 500;
	/**
	 *
	 */
	private static final long serialVersionUID = 2111502979187740671L;
	private static final String SIMULATION_SPEED_FORMAT = "{0} ms";

	private BoundedRangeModel loadPersonsSpeedModel = new DefaultBoundedRangeModel();

	public LoadSpeedSimulationPanel() {
		setLayout(null);
		loadPersonsSpeedModel.setMinimum(0);
		loadPersonsSpeedModel.setMaximum(MAX_LOAD_SPEED_MS);
		loadPersonsSpeedModel.setValue(INITIAL_LOAD_SPEED_MS);
		loadPersonsSpeedModel.setExtent(0);

		JSlider loadSpeedSlider = new JSlider();
		loadSpeedSlider.setMinorTickSpacing(MIN_LOAD_SPEED_TICK_MS);
		loadSpeedSlider.setSnapToTicks(true);
		loadSpeedSlider.setPaintTicks(true);
		loadSpeedSlider.setPaintLabels(true);
		loadSpeedSlider.setBounds(161, 12, 429, 43);
		loadSpeedSlider.setModel(loadPersonsSpeedModel);
		add(loadSpeedSlider);

		JLabelTextComponent selectedLoadSpeed = new JLabelTextComponent("");
		selectedLoadSpeed.setFont(new Font("Tahoma", Font.PLAIN, 12));
		selectedLoadSpeed.setBounds(10, 38, 138, 14);
		add(selectedLoadSpeed);

		// adapt the jLabel to the Document model to support MVC.

		MessageFormat speedLabelFormat = new MessageFormat(
				SIMULATION_SPEED_FORMAT);
		Document loadSpeedDocument = selectedLoadSpeed.getDocument();
		BoundedRangeModel2DocumentAdapter boundedRangeAdapter = new BoundedRangeModel2DocumentAdapter(
				loadSpeedDocument, speedLabelFormat);
		boundedRangeAdapter.bind(loadPersonsSpeedModel);

		JLabel label = new JLabel("Load Speed Simulation");
		label.setFont(new Font("Tahoma", Font.PLAIN, 12));
		label.setBounds(10, 11, 138, 14);
		add(label);

		setPreferredSize(new Dimension(600, 60));
	}

	public BoundedRangeModel getPersonsLoadSpeedModel() {
		return loadPersonsSpeedModel;
	}
}
