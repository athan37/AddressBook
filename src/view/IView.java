package view;

import javax.swing.JTable;

public interface IView {
	/**
	 * Set this view visible
	 */
	public void setVisible(boolean visible);
	public void addFeatures(Features features);
	public JTable getTable();
}
