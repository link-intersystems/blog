package com.link_intersystems.blog.mvc.controller;

import java.util.ArrayList;
import java.util.List;

import javax.swing.BoundedRangeModel;
import javax.swing.DefaultBoundedRangeModel;
import javax.swing.SwingWorker;

import com.link_intersystems.blog.mvc.data.Address;
import com.link_intersystems.blog.mvc.data.Person;
import com.link_intersystems.blog.mvc.model.list.ListAdapterListModel;

public class LoadPersonsWorker extends SwingWorker<List<Person>, Person> {

	private volatile int maxProgress;
	private int progressedItems;
	private ListAdapterListModel<Person> personListModel;
	private BoundedRangeModel loadPersonsSpeedModel = new DefaultBoundedRangeModel();

	public LoadPersonsWorker(ListAdapterListModel<Person> personListModel) {
		this.personListModel = personListModel;
	}

	@Override
	protected List<Person> doInBackground() throws Exception {
		personListModel.clear();
		maxProgress = 3;
		List<Person> persons = new ArrayList<Person>();

		// Simulate progress
		sleepAWhile();

		Person person1 = new Person();
		person1.setFirstname("Michel");
		person1.setLastname("Smith");
		Address address1 = new Address();
		address1.setCity("San Francisco");
		address1.setZipCode("CA 94114");
		address1.setStreet("Jersey St");
		address1.setStreetNr("222");
		person1.setAddress(address1);
		persons.add(person1);
		publish(person1);

		// Simulate progress
		sleepAWhile();

		Person person2 = new Person();
		person2.setFirstname("John");
		person2.setLastname("Wilson");
		Address address2 = new Address();
		address2.setStreet("Carbernet Ave");
		address2.setStreetNr("6934");
		address2.setCity("Newark");
		address2.setZipCode("CA 94560");
		person2.setAddress(address2);
		persons.add(person2);
		publish(person2);

		// Simulate progress
		sleepAWhile();

		Person person3 = new Person();
		person3.setFirstname("Roland");
		person3.setLastname("Anderson");
		Address address3 = new Address();
		address3.setCity("Las Vegas");
		address3.setZipCode("NV 89130");
		address3.setStreet("Grennery Ct");
		address3.setStreetNr("6908");
		person3.setAddress(address3);
		persons.add(person3);
		publish(person3);
		sleepAWhile();

		return persons;
	}

	@Override
	protected void process(List<Person> chunks) {
		personListModel.addAll(chunks);
		progressedItems = progressedItems + chunks.size();
		setProgress(calcProgress(progressedItems));
	}

	private int calcProgress(int progressedItems) {
		int progress = (int) ((100.0 / (double) maxProgress) * (double) progressedItems);
		return progress;
	}

	private void sleepAWhile() {
		try {
			Thread.yield();
			long timeToSleep = getTimeToSleep();
			Thread.sleep(timeToSleep);
		} catch (InterruptedException e) {
		}
	}

	private long getTimeToSleep() {
		return loadPersonsSpeedModel.getValue();
	}

	public void setLoadSpeedModel(BoundedRangeModel loadPersonsSpeedModel) {
		this.loadPersonsSpeedModel = loadPersonsSpeedModel;

	}
}
