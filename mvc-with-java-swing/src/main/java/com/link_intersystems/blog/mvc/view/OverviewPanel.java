package com.link_intersystems.blog.mvc.view;

import javax.swing.DefaultListSelectionModel;
import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTree;
import javax.swing.ListCellRenderer;
import javax.swing.ListSelectionModel;

import com.link_intersystems.blog.mvc.data.Person;
import com.link_intersystems.blog.mvc.model.list.ListAdapterListModel;
import com.link_intersystems.blog.mvc.model.list.ListComboBoxModel;
import com.link_intersystems.blog.mvc.model.list.ListModelSelection;
import com.link_intersystems.blog.mvc.model.list.PersonListCellRenderer;
import com.link_intersystems.blog.mvc.model.table.PersonTableModel;
import com.link_intersystems.blog.mvc.model.tree.PersonTreeCellRenderer;
import com.link_intersystems.blog.mvc.model.tree.PersonTreeModel;
import com.link_intersystems.blog.mvc.model.tree.TreeListModelSelectionAdapter;

/**
 * The main panel that holds a {@link JList}, {@link JTable}, {@link JTree} and
 * {@link JComboBox} that display the loaded {@link Person} objects. All the
 * components are synchronized by models so that they will all show the same
 * data and reflect the same selection. This panel is an example of how to
 * implement a MVC (model-view-controller) pattern with swing.
 *
 * @author René Link <a
 *         href="mailto:rene.link@link-intersystems.com">[rene.link@link-
 *         intersystems.com]</a>
 *
 */
public class OverviewPanel extends JPanel {

	/**
	 *
	 */
	private static final long serialVersionUID = -4471606875093169644L;
	private PersonTreeModel personTreeModel = new PersonTreeModel();
	private PersonTableModel personTableModel = new PersonTableModel();
	private ListAdapterListModel<Person> personListModel = new ListAdapterListModel<Person>();
	private TreeListModelSelectionAdapter treeSelectionListModelSelectionAdapter = new TreeListModelSelectionAdapter(
			personTreeModel);
	private ListModelSelection<Person> listModelSelection = new ListModelSelection<Person>();
	private ListSelectionModel selectionModel = new DefaultListSelectionModel();

	private JTable personTable = new JTable(personTableModel);
	private JList<Person> personList = new JList<Person>(personListModel);
	private JTree personTree = new JTree(personTreeModel);
	private ListComboBoxModel<Person> personComboBoxModel = new ListComboBoxModel<Person>();
	private JComboBox<Person> personsComboBox = new JComboBox<Person>(
			personComboBoxModel);

	public void setPersonList(ListAdapterListModel<Person> personListModel) {
		personList.setModel(personListModel);
		personTableModel.setListModel(personListModel);
		personTreeModel.setListModel(personListModel);
		personComboBoxModel.setListModel(personListModel);
		listModelSelection.setListModels(personListModel, selectionModel);
	}

	public OverviewPanel() {
		setLayout(null);
		selectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		treeSelectionListModelSelectionAdapter
				.setListModelSelection(listModelSelection);
		personList.setSelectionModel(selectionModel);
		personTable.setSelectionModel(selectionModel);
		personComboBoxModel.setListSelectionModel(selectionModel);
		personTree.setSelectionModel(treeSelectionListModelSelectionAdapter);

		ListCellRenderer<Person> personRenderer = new PersonListCellRenderer();
		personList.setCellRenderer(personRenderer);
		personList.setEnabled(true);
		personsComboBox.setRenderer(personRenderer);
		personTree.setCellRenderer(new PersonTreeCellRenderer());
		personTree.setRootVisible(false);
		personTable.setSelectionModel(personList.getSelectionModel());

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 580, 130);
		add(scrollPane);

		scrollPane.setViewportView(personTable);

		personsComboBox.setBounds(10, 153, 580, 30);
		add(personsComboBox);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 194, 580, 105);
		add(scrollPane_1);

		scrollPane_1.setViewportView(personList);

		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(10, 310, 580, 205);
		add(scrollPane_2);

		scrollPane_2.setColumnHeaderView(personTree);
	}

}
