package com.link_intersystems.blog.swing.list;

import java.text.MessageFormat;

import javax.swing.JList;
import javax.swing.ListModel;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

/**
 * A {@link PlainDocument} that listens to {@link ListSelectionEvent}s to change
 * it's text in order to the selected list elements.
 *
 * @author René Link [rene.link@link-intersystems.com]
 *
 */
public class ListSelectionDocument extends PlainDocument implements
		ListSelectionListener {

	private static final long serialVersionUID = 2710696851334704262L;

	private MessageFormat elementFormat;
	private String delim;

	/**
	 * Creates a {@link ListSelectionDocument} whose text is the concatenation
	 * of each selected list element's string representation delimited by a new
	 * line.
	 */
	public ListSelectionDocument() {
		this(new MessageFormat("{0}"), "\n");
	}

	/**
	 * Creates a new {@link ListSelectionDocument} whose text is the
	 * concatenation of each selected list element's string representation as
	 * formatted by the given {@link MessageFormat} delimited by a the given
	 * delimiter.
	 */
	public ListSelectionDocument(MessageFormat elementFormat, String delim) {
		this.elementFormat = elementFormat;
		this.delim = delim;
	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
		JList<?> list = (JList<?>) e.getSource();
		ListModel<?> model = list.getModel();

		ListSelectionModel listSelectionModel = list.getSelectionModel();

		int minSelectionIndex = listSelectionModel.getMinSelectionIndex();
		int maxSelectionIndex = listSelectionModel.getMaxSelectionIndex();

		StringBuilder textBuilder = new StringBuilder();

		for (int i = minSelectionIndex; i <= maxSelectionIndex; i++) {
			if (listSelectionModel.isSelectedIndex(i)) {
				Object elementAt = model.getElementAt(i);
				formatElement(elementAt, textBuilder, i);
			}
		}

		setText(textBuilder.toString());
	}

	private void formatElement(Object element, StringBuilder textBuilder, int i) {
		String formatted = elementFormat.format(new Object[] { element });
		textBuilder.append(formatted);
		textBuilder.append(delim);
	}

	private void setText(String text) {
		try {
			remove(0, getLength());
			insertString(0, text, null);
		} catch (BadLocationException e1) {
		}
	}
}