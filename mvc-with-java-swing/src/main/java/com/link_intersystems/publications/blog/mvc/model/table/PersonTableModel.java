package com.link_intersystems.publications.blog.mvc.model.table;

import java.util.Observer;

import javax.swing.DefaultListModel;
import javax.swing.JTable;
import javax.swing.ListModel;
import javax.swing.event.ListDataEvent;
import javax.swing.event.ListDataListener;
import javax.swing.table.AbstractTableModel;

import com.link_intersystems.publications.blog.mvc.data.Address;
import com.link_intersystems.publications.blog.mvc.data.Person;

/**
 * A {@link PersonTableModel} adds a table perspective on a {@link PersonsModel}
 * to adapt it to a {@link JTable}. Therefore it listens to updates of the
 * {@link PersonsModel} by implementing the {@link Observer} interface.
 *
 * @author rene.link
 *
 */
public class PersonTableModel extends AbstractTableModel {

	/**
	 *
	 */
	private static final long serialVersionUID = 1547542546403627396L;

	private enum Columns {
		FIRSTNAME, LASTNAME, STREET, STREET_NR, ZIP_CODE, CITY
	}

	private ListModel<Person> listModel = new DefaultListModel<Person>();
	private ListModelChangeListener listModelChangeListener = new ListModelChangeListener();

	public PersonTableModel() {
	}

	public final void setListModel(ListModel<Person> listModel) {
		if (this.listModel != null) {
			this.listModel.removeListDataListener(listModelChangeListener);
		}
		this.listModel = listModel;
		if (listModel != null) {
			listModel.addListDataListener(listModelChangeListener);
		}
		fireTableDataChanged();
	}

	public int getRowCount() {
		return listModel.getSize();
	}

	public int getColumnCount() {
		return Columns.values().length;
	}

	public Object getValueAt(int rowIndex, int columnIndex) {
		Object columnValue = null;
		Person person = (Person) listModel.getElementAt(rowIndex);
		Columns[] columns = Columns.values();
		Columns column = columns[columnIndex];

		switch (column) {
		case FIRSTNAME:
			columnValue = person.getFirstname();
			break;
		case LASTNAME:
			columnValue = person.getLastname();
			break;
		default:
			columnValue = getAddressObject(person, column);
			break;
		}

		return columnValue;
	}

	private Object getAddressObject(Person person, Columns column) {
		Object columnValue = null;
		Address address = person.getAddress();
		if (address != null) {
			switch (column) {
			case STREET:
				columnValue = address.getStreet();
				break;
			case STREET_NR:
				columnValue = address.getStreetNr();
				break;
			case CITY:
				columnValue = address.getCity();
				break;
			case ZIP_CODE:
				columnValue = address.getZipCode();
				break;
			default:
				break;
			}
		}
		return columnValue;
	}

	@Override
	public Class<?> getColumnClass(int columnIndex) {
		return String.class;
	}

	@Override
	public String getColumnName(int column) {
		Columns[] columns = Columns.values();
		Columns columnsObj = columns[column];

		String columnName = null;
		switch (columnsObj) {
		case FIRSTNAME:
			columnName = "Firstname";
			break;
		case LASTNAME:
			columnName = "Lastname";
			break;
		case STREET:
			columnName = "Street";
			break;
		case STREET_NR:
			columnName = "Street Nr.";
			break;
		case ZIP_CODE:
			columnName = "Zip Code";
			break;
		case CITY:
			columnName = "City";
			break;

		default:
			break;
		}
		return columnName;
	}

	private class ListModelChangeListener implements ListDataListener {

		public void intervalAdded(ListDataEvent e) {
			fireTableDataChanged();
		}

		public void intervalRemoved(ListDataEvent e) {
			fireTableDataChanged();
		}

		public void contentsChanged(ListDataEvent e) {
			fireTableDataChanged();
		}

	}

}
