package view;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import model.Gender;
import model.IModel;

import javax.swing.JLabel;
import java.awt.FlowLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.awt.event.ActionEvent;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JTable;

public class ContactViewImp extends JFrame implements IView {
	public ContactViewImp() {
	}
	
	@Override
	public void addFeatures(Features features) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public JTable getTable() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
