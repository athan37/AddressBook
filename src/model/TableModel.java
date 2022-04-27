package model;

import java.util.List;
import java.util.stream.Collectors;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.AbstractTableModel;

public class TableModel extends AbstractTableModel {
	private String[] columnNames;
	private Object[][] data;
	
	/**
	 * Constructor for this table model that require to input
	 * a list of contacts
	 * @param data
	 */
	public TableModel(List<Contact> data) {
		setColumnNames(new String[]{ "First Name", "Last Name" , "Gender", "Number", "Age", "Email"});
		setData(data);
	}

	/**
	 * Return the number of rows
	 * @return number of rows
	 */
	@Override
	public int getRowCount() {
		return data.length;
	}

	/**
	 * Return the number of columns
	 * @return number of columns
	 */
	@Override
	public int getColumnCount() {
		return columnNames.length;
	}

	/**
	 * Return the value at the a specific location
	 * @return the value at the a specific location
	 */
	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		return data[rowIndex][columnIndex];
	}
	
	/**
	 * Set up each of the column's name
	 */
    @Override
    public String getColumnName(int index) {
        return columnNames[index];
    }
    
	/**
	 * @return the columnNames
	 */
	public String[] getColumnNames() {
		return columnNames;
	}

	/**
	 * @param columnNames the columnNames to set
	 */
	public void setColumnNames(String[] columnNames) {
		this.columnNames = columnNames;
	}

	/**
	 * @return the data
	 */
	public Object[][] getData() {
		return data;
	}

	/**
	 * @param data the data to set
	 */
	public void setData(List<Contact> list) {
		this.data = list.stream()
			.map(e -> new Object[]{
						e.getFirstName(), e.getLastName(), 
						e.getGender(), e.getNumber(), 
						e.getAge(), e.getEmail()})
			.collect(Collectors.toList())
			.toArray(Object[][] :: new);
	}
	
	
}
