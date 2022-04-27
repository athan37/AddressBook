package view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import java.awt.CardLayout;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.RowFilter;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import controller.Controller;
import controller.IController;
import model.Contact;
import model.Gender;
import model.IModel;
import model.Model;

import javax.swing.JTable;

public class MainView extends JFrame implements IView {
	private JTextField textField;
	private JTable table;
	private JButton addBtn;
	private JButton deleteBtn;
	private IModel model;
	private IController controller;
	
	//Panels
	private JPanel dataPanel;
	private JPanel tablePanel;
	private JPanel updatePanel;
	
	
	private void initComponents() {
		setSize(new Dimension(800, 500));
		
//		updateBtn = new JButton("Update");
		addBtn    = new JButton("Add");
		deleteBtn = new JButton("Delete");
		table     = new JTable();
		textField = new JTextField();
		controller= new Controller(model, this);
		
		dataPanel   = new JPanel(new CardLayout());
		tablePanel  = new JPanel();
		updatePanel = new EditPanel(controller, "","", Gender.M, "", 0, "");
		
		//Setup table + scroll
		table     = new JTable(((Model) model).getTableModel());
		JScrollPane contactScrollPane = new JScrollPane(table);
		table.setFillsViewportHeight(true);
		tablePanel.add(contactScrollPane);
		
		table.getSelectionModel().addListSelectionListener(
				new ListSelectionListener() {
					@Override
					public void valueChanged(ListSelectionEvent e) {
						if (table.getSelectionModel().isSelectionEmpty()) return;
						controller.viewContact(new Object[]{
							table.getValueAt(table.getSelectedRow(), 0),
							table.getValueAt(table.getSelectedRow(), 1),
							table.getValueAt(table.getSelectedRow(), 2),
							table.getValueAt(table.getSelectedRow(), 3),
							table.getValueAt(table.getSelectedRow(), 4),
							table.getValueAt(table.getSelectedRow(), 5),
						});
					}
				}
		);
		
		//Add sub layout card layout
		dataPanel.add(tablePanel, "table");
		dataPanel.add(updatePanel, "update");
		
		
		textField.setColumns(10);
		textField.getDocument().addDocumentListener(
				new DocumentListener() {

					@Override
					public void insertUpdate(DocumentEvent e) {
						controller.search(textField.getText());
					}

					@Override
					public void removeUpdate(DocumentEvent e) {
						controller.search(textField.getText());
					}

					@Override
					public void changedUpdate(DocumentEvent e) {
						// TODO Auto-generated method stub
					}
					
				}
		);
		
		
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(49)
					.addComponent(textField, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(addBtn, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(deleteBtn, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE)
					.addGap(40)
					.addContainerGap(218, Short.MAX_VALUE))
				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
					.addContainerGap(41, Short.MAX_VALUE)
					.addComponent(dataPanel, GroupLayout.PREFERRED_SIZE, 800, GroupLayout.PREFERRED_SIZE)
					.addGap(36))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(43)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(deleteBtn)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
							.addComponent(addBtn)
							.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							))
					.addGap(54)
					.addComponent(dataPanel, GroupLayout.PREFERRED_SIZE, 300, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(61, Short.MAX_VALUE))
		);
		getContentPane().setLayout(groupLayout);
		
		addFeatures(null);
		
	}
	
	public MainView(IModel model) {
		this.model = model;
		initComponents();
		
		setVisible(true);
	}
	

	@Override
	public void addFeatures(Features features) {
//		searchBtn.addActionListener(evt -> controller.search(textField.getText()));
//		addBtn.addActionListener(e -> controller.openAddDialogue());
//		updateBtn.addActionListener(evt -> controller.viewContact());
	}
	
	
	
	/**
	 * @return the table
	 */
	public JTable getTable() {
		return table;
	}

	/**
	 * @param table the table to set
	 */
	public void setTable(JTable table) {
		this.table = table;
	}
	
	

	/**
	 * @return the dataPanel
	 */
	public JPanel getDataPanel() {
		return dataPanel;
	}

	/**
	 * @param dataPanel the dataPanel to set
	 */
	public void setDataPanel(JPanel dataPanel) {
		this.dataPanel = dataPanel;
	}

	/**
	 * @return the tablePanel
	 */
	public JPanel getTablePanel() {
		return tablePanel;
	}

	/**
	 * @param tablePanel the tablePanel to set
	 */
	public void setTablePanel(JPanel tablePanel) {
		this.tablePanel = tablePanel;
	}

	/**
	 * @return the updatePanel
	 */
	public JPanel getUpdatePanel() {
		return updatePanel;
	}

	/**
	 * @param updatePanel the updatePanel to set
	 */
	public void setUpdatePanel(JPanel updatePanel) {
		this.dataPanel.add(updatePanel, "update");
	}

	/**
	 * @return the textField
	 */
	public JTextField getTextField() {
		return textField;
	}

	/**
	 * @param textField the textField to set
	 */
	public void setTextField(JTextField textField) {
		this.textField = textField;
	}
	
	

	public static void main(String[] args) {
		List<Contact> contacts = new ArrayList<>(Arrays.asList(
			new Contact("Kathy", "Smith", Gender.F, "0123456789", 18, "saf@asdc.com"),
			new Contact("Strong", "Smile", Gender.M, "0123456789", 18, "saf@asdc.com"),
			new Contact("Strenth", "Smile", Gender.M, "0123456789", 18, "saf@asdc.com"),
			new Contact("Manny", "Smile", Gender.M, "0123456789", 18, "saf@asdc.com"),
			new Contact("Menny", "Rigge", Gender.F, "0123456789", 18, "saf@asdc.com")
		));
		
		IModel model = new Model(contacts);
		IView view   = new MainView(model);
		IController controller = new Controller(model, view);
	}

}
