package com.link_intersystems.blog.mvc.view;

import javax.swing.Icon;
import javax.swing.JLabel;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.PlainDocument;

public class JLabelTextComponent extends JLabel {

	/**
	 *
	 */
	private static final long serialVersionUID = -888409160811409471L;

	private DocumentListenerAdapter documentListenerAdapter = new DocumentListenerAdapter();

	private Document document = new PlainDocument();

	public JLabelTextComponent() {
		super();
		initDocument(document);
		setDocument(document);
	}

	public JLabelTextComponent(Icon image, int horizontalAlignment) {
		super(image, horizontalAlignment);
		initDocument(document);
		setDocument(document);
	}

	public JLabelTextComponent(Icon image) {
		super(image);
		initDocument(document);
		setDocument(document);
	}

	public JLabelTextComponent(String text, Icon icon, int horizontalAlignment) {
		super(text, icon, horizontalAlignment);
		initDocument(document);
		setDocument(document);
	}

	public JLabelTextComponent(String text, int horizontalAlignment) {
		super(text, horizontalAlignment);
		initDocument(document);
		setDocument(document);
	}

	public JLabelTextComponent(String text) {
		super(text);
		initDocument(document);
		setDocument(document);
	}

	/**
	 * Initializes the document with the current {@link #getText()}.
	 * 
	 * @param document
	 */
	private void initDocument(Document document) {
		try {
			String text = getText();
			document.remove(0, document.getLength());
			document.insertString(0, text, null);
		} catch (BadLocationException e) {
		}
	}

	public final void setDocument(Document document) {
		if (this.document != null) {
			this.document.removeDocumentListener(documentListenerAdapter);
		}
		this.document = document;
		if (document != null) {
			document.addDocumentListener(documentListenerAdapter);
		}
	}

	public Document getDocument() {
		return document;
	}

	private class DocumentListenerAdapter implements DocumentListener {

		@Override
		public void insertUpdate(DocumentEvent e) {
			Document document = e.getDocument();
			updateJLabel(document);
		}

		@Override
		public void removeUpdate(DocumentEvent e) {
			Document document = e.getDocument();
			updateJLabel(document);
		}

		@Override
		public void changedUpdate(DocumentEvent e) {
			Document document = e.getDocument();
			updateJLabel(document);
		}

		private void updateJLabel(Document document) {
			String text = null;
			try {
				text = document.getText(0, document.getLength());
				JLabelTextComponent.super.setText(text);
			} catch (BadLocationException e) {
			}
		}

	}
}
