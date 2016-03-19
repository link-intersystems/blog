package com.link_intersystems.example;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import javax.swing.text.BadLocationException;
import javax.swing.text.Document;

import com.link_intersystems.swing.ProgressAction;
import com.link_intersystems.swing.ProgressWorker;

public class ProgressSimulationAction extends ProgressAction<String> {

	private static final long serialVersionUID = 1L;

	public Document resultDocument;

	public void setResultDocument(Document resultDocument) {
		this.resultDocument = resultDocument;
	}

	@Override
	protected ProgressWorker<String> createProgressWorker() {
		ProgressWorker<String> progressSimulationWorker = new ProgressSimulationWorker();
		clearDocument();
		return progressSimulationWorker;
	}

	protected void done(Future<String> result) {
		if (result.isCancelled()) {
			return;
		}

		if (resultDocument != null) {
			try {
				String resultText = result.get();
				setText(resultDocument, resultText);
			} catch (InterruptedException | ExecutionException e) {
			}
		}
	}

	private void setText(Document document, String text) {
		try {
			clearDocument();
			resultDocument.insertString(0, text, null);
		} catch (BadLocationException e) {
		}
	}

	private void clearDocument() {
		try {
			resultDocument.remove(0, resultDocument.getLength());
		} catch (BadLocationException e) {
		}
	}
}