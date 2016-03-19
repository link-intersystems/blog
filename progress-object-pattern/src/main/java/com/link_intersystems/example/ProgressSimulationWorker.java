package com.link_intersystems.example;

import java.text.MessageFormat;

import com.link_intersystems.swing.ProgressWorker;

public class ProgressSimulationWorker extends ProgressWorker<String> {

	@Override
	protected String doInBackground() throws Exception {
		setProgress(0);

		for (int i = 0; i <= 100; i++) {
			if (isCancelled()) {
				break;
			}
			Thread.sleep(50);
			setProgress(i);
		}

		String msg = null;
		if (!isCancelled()) {
			msg = MessageFormat.format("Thank you {0} for running the progress example",
					System.getProperty("user.name"));
			setProgress(100);
		}
		return msg;

	}

}
