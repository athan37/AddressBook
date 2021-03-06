package view;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import controller.Controller;
import controller.IController;
import model.Contact;
import model.Gender;
import model.IModel;
import model.Model;

import javax.swing.JTable;

/**
 * Class represents the main view of the application.
 * It has a control panel (search field, add, sort buttons) and a
 * data panel that either holds a table or holds a form.
 * @author Duc Anh
 *
 */
public class MainView extends JFrame implements IView {
	private JTextField textField;
	private JTable table;
	private JButton addBtn;
	private JLabel sortLabel;
	private JComboBox<String> sortSelect;
	private IController controller;
	private IModel model;
	
	//Panels
	private JPanel dataPanel;
	private JPanel tablePanel;
	private JPanel updatePanel;
	
	@Override
	public void initComponents() {
		setSize(new Dimension(800, 550));
		
		addBtn    = new JButton("Add");
		sortLabel = new JLabel("Sort by: ");
		sortSelect= new JComboBox<String>(new String[] {"First Name", "Last Name", "Age", "Email", "Gender", "Number"}) ;
		table     = new JTable();
		textField = new JTextField();
		
		model      = new Model();
		controller = new Controller(model, this);
		
		dataPanel   = new JPanel(new CardLayout());
		tablePanel  = new JPanel();
		updatePanel = new AddForm(controller);
		
		sortSelect.setSize(500, 50);
		sortSelect.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.searchAndSort(textField.getText(), sortSelect.getSelectedItem().toString());
			}
		});
		
		//Setup table + scroll
		table     = new JTable(controller.getContactTableData());
		JScrollPane contactScrollPane = new JScrollPane(table);
		table.setFillsViewportHeight(true);
		tablePanel.add(contactScrollPane);
		
		//Bind some actions to component: table + buttons
		table.getSelectionModel().addListSelectionListener(
				new ListSelectionListener() {
					@Override
					public void valueChanged(ListSelectionEvent e) {
						if (table.getSelectionModel().isSelectionEmpty()) return;
						controller.viewSelectedContact(new Object[]{
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
		
		addBtn.addActionListener(evt -> controller.viewNewContact());
		
		//Add sub layout card layout
		dataPanel.add(tablePanel, "table");
		dataPanel.add(updatePanel, "update");
		
		
		textField.setColumns(10);
		textField.getDocument().addDocumentListener(
				new DocumentListener() {

					@Override
					public void insertUpdate(DocumentEvent e) {
						controller.searchAndSort(textField.getText(), sortSelect.getSelectedItem().toString());
					}

					@Override
					public void removeUpdate(DocumentEvent e) {
						controller.searchAndSort(textField.getText(), sortSelect.getSelectedItem().toString());
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
					.addContainerGap(50, Short.MAX_VALUE)
					.addGap(60)
					.addComponent(sortLabel, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE)
					.addComponent(sortSelect, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(446, Short.MAX_VALUE))
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addComponent(dataPanel, GroupLayout.PREFERRED_SIZE, 800, GroupLayout.PREFERRED_SIZE)
					.addGap(36)
				)
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(43)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(addBtn)
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(sortLabel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(sortSelect, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					)
					.addGap(54)
					.addComponent(dataPanel, GroupLayout.PREFERRED_SIZE, 300, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(95, Short.MAX_VALUE))
		);
		getContentPane().setLayout(groupLayout);
		
		setClosingEvent();
		
	}
	
	private void setClosingEvent() {
		this.addWindowListener(new WindowAdapter() {
			@Override
		    public void windowClosing(WindowEvent e) {
		    	controller.closeApp();
		    	System.exit(0);
		    }
		});
	}
	
	public MainView() {
		initComponents();
		
		setVisible(true);
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
	 * @return the sortSelect
	 */
	public JComboBox<String> getSortSelect() {
		return sortSelect;
	}

	/**
	 * @param sortSelect the sortSelect to set
	 */
	public void setSortSelect(JComboBox<String> sortSelect) {
		this.sortSelect = sortSelect;
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
	
	/**
	 * @return the addBtn
	 */
	public JButton getAddBtn() {
		return addBtn;
	}

	/**
	 * @param addBtn the addBtn to set
	 */
	public void setAddBtn(JButton addBtn) {
		this.addBtn = addBtn;
	}
	
	

}
