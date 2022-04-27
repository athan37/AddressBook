package controller;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.function.Predicate;

import javax.swing.JPanel;

import model.Contact;
import model.Gender;
import model.IModel;
import model.Model;
import model.TableModel;
import view.EditPanel;
import view.Features;
import view.IView;
import view.MainView;

public class Controller implements ActionListener, IController{
	private IModel model;
	private IView view;
	
	public Controller(IModel model, IView view) {
		this.model = model;
		this.view  = view;
	}
	
	@Override
	public void search(String prefix) {
		view.getTable().setModel(new TableModel(model.search(prefix)));
	}
	
//	public void setView(IView v) {
//		view = v;
//		view.addFeatures(this);
//	}

	public List<Contact> findContact(Predicate<Contact> predicate) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		switch(e.getActionCommand()) {
			
		}
	}
	
	private void chooseDataPanel(String panelName) {
		JPanel cards = ((MainView) view).getDataPanel();
		CardLayout cl = ((CardLayout) cards.getLayout());
		cl.show(cards, panelName);
	}

	@Override
	public void viewContact(Object[] contactData) {
		System.out.println(contactData);
		((MainView) view).getTextField().setEnabled(false);
		System.out.println("Let's see why it's have nothing" + new ArrayList<>(Arrays.asList(contactData)));
		((MainView) view).setUpdatePanel(
				new EditPanel(
						this, 
						(String) contactData[0],
						(String) contactData[1],
						(Gender) contactData[2],
						(String) contactData[3],
						(Integer) contactData[4],
						(String) contactData[5]
						));
		chooseDataPanel("update");
	}

	@Override
	public void updateContact(Object[] oldContactData, Object[] newContactData) {
		model.updateOneContact(model.convertDataToContact(oldContactData), model.convertDataToContact(newContactData));
		((MainView) view).getTextField().setEnabled(true);
		((MainView) view).getTextField().setText("");
//		((MainView) view).setUpdatePanel(
//		);
		System.out.println("YEASFKD" + model.getAllContacts());
		view.getTable().setModel(new TableModel(model.getAllContacts()));
		chooseDataPanel("table");
		
	}

}
