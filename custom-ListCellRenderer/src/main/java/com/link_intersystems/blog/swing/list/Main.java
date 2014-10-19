package com.link_intersystems.blog.swing.list;

import java.awt.BorderLayout;
import java.awt.Container;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JTextArea;
import javax.swing.ListModel;
import javax.swing.WindowConstants;
import javax.swing.border.Border;

public class Main {

	public static void main(String[] args) {
		JFrame jFrame = createMainFrame();

		Container contentPane = jFrame.getContentPane();
		contentPane.setLayout(new BorderLayout());

		ListModel<String> listModel = createListModel();
		ListSelectionDocument listSelectionDocument = new ListSelectionDocument();

		JList<String> list = new JList<String>();
		list.setCellRenderer(new CheckboxListCellRenderer<String>());
		list.setModel(listModel);
		list.addListSelectionListener(listSelectionDocument);

		JTextArea listSelectionTextArea = new JTextArea(listSelectionDocument);
		Border loweredBevelBorder = BorderFactory.createLoweredBevelBorder();
		listSelectionTextArea.setBorder(loweredBevelBorder);

		contentPane.add(list, BorderLayout.CENTER);
		contentPane.add(listSelectionTextArea, BorderLayout.SOUTH);

		jFrame.setVisible(true);
	}

	private static JFrame createMainFrame() {
		JFrame jFrame = new JFrame("Custom ListCellRenderer Example");
		jFrame.setSize(240, 240);
		jFrame.setLocationRelativeTo(null);
		jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		return jFrame;
	}

	private static DefaultListModel<String> createListModel() {
		DefaultListModel<String> listModel = new DefaultListModel<String>();

		listModel.addElement("Element 1");
		listModel.addElement("Element 2");
		listModel.addElement("Element 3");
		listModel.addElement("Element 4");

		return listModel;
	}

}
