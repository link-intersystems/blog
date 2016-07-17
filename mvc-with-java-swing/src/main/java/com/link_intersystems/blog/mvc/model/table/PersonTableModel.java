package com.link_intersystems.blog.mvc.model.table;

import java.util.HashMap;
import java.util.Map;

import javax.swing.JTable;
import javax.swing.ListModel;
import javax.swing.event.ListDataEvent;
import javax.swing.event.ListDataListener;
import javax.swing.table.AbstractTableModel;

import com.link_intersystems.blog.mvc.data.Address;
import com.link_intersystems.blog.mvc.data.Person;
import com.link_intersystems.blog.mvc.model.list.ListModelHolder;

/**
 * A {@link PersonTableModel} adds a table perspective on a {@link ListModel}
 * that contains {@link Person} model objects to adapt it to a {@link JTable}.
 * Therefore it listens to updates of the {@link ListModel} by adapting the
 * {@link ListDataListener} interface.
 *
 * @author rene.link
 *
 */
public class PersonTableModel extends AbstractTableModel {

	private static final long serialVersionUID = 1547542546403627396L;

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

	private enum Column {
		FIRSTNAME, LASTNAME, STREET, STREET_NR, ZIP_CODE, CITY
	}

	private ListModelHolder<Person> personListModelHolder = new ListModelHolder<Person>();
	private ListModelChangeListener listModelChangeListener = new ListModelChangeListener();

	private Map<Column, String> columnDisplayNames = new HashMap<PersonTableModel.Column, String>();

	public PersonTableModel() {
		columnDisplayNames.put(Column.FIRSTNAME, "Firstname");
		columnDisplayNames.put(Column.LASTNAME, "Lastname");
		columnDisplayNames.put(Column.STREET, "Street");
		columnDisplayNames.put(Column.STREET_NR, "Street Nr.");
		columnDisplayNames.put(Column.ZIP_CODE, "Zip Code");
		columnDisplayNames.put(Column.CITY, "City");

		personListModelHolder.addListDataListeners(listModelChangeListener);
	}

	public final void setListModel(ListModel<Person> listModel) {
		personListModelHolder.setModel(listModel);
	}

	public int getRowCount() {
		ListModel<Person> listModel = personListModelHolder.getModel();
		return listModel.getSize();
	}

	public int getColumnCount() {
		return Column.values().length;
	}

	public Object getValueAt(int rowIndex, int columnIndex) {
		Object columnValue = null;

		ListModel<Person> listModel = personListModelHolder.getModel();
		Person person = listModel.getElementAt(rowIndex);
		Column column = getColumn(columnIndex);

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

	private Column getColumn(int columnIndex) {
		Column[] columns = Column.values();
		Column column = columns[columnIndex];
		return column;
	}

	private Object getAddressObject(Person person, Column column) {
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
		Column columnObj = getColumn(column);
		String displayName = columnDisplayNames.get(columnObj);
		return displayName;
	}
}
