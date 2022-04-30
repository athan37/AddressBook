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
import view.AddContactPanel;
import view.AppView;
import view.EditContactPanel;
import view.Features;
import view.IView;
import view.MainView;

public class Controller implements ActionListener, IController {
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
	
	@Override
	public TableModel getContactTableData() {
		return ((Model) model).getTableModel();
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
	
	/**
	 * This method is a helper to turn off the search 
	 * and the add button during the VIEW of the data of 
	 * any contact
	 */
	private void disableControlMainView() {
		((MainView) view).getTextField().setEnabled(false);
		((MainView) view).getAddBtn().setEnabled(false);
	}
	
	private void enableControlMainView() {
		((MainView) view).getTextField().setEnabled(true);
		((MainView) view).getTextField().setText("");
		((MainView) view).getAddBtn().setEnabled(true);
	}

	@Override
	public void viewSelectedContact(Object[] contactData) {
		disableControlMainView();
		((MainView) view).setUpdatePanel(
				new EditContactPanel(
						this, 
						(String) contactData[0],
						(String) contactData[1],
						(Gender) contactData[2],
						(String) contactData[3],
						(Integer)contactData[4],
						(String) contactData[5]
						));
		chooseDataPanel("update");
	}
	
	@Override
	public void viewNewContact() {
		disableControlMainView();
		((MainView) view).setUpdatePanel(new AddContactPanel(this));
		chooseDataPanel("update");
	}
	
	@Override
	public void addContact(Object[] newContactData) {
		//Check again the method equals
		if (newContactData.equals(new Object[]{"","", Gender.M, "", 0, ""})) return;
		model.addOneContact(model.convertDataToContact(newContactData));
		enableControlMainView();
		view.getTable().setModel(new TableModel(model.getAllContacts()));
		chooseDataPanel("table");
	}

	@Override
	public void updateContact(Object[] oldContactData, Object[] newContactData) {
		if (!oldContactData.equals(newContactData)) {
			model.updateOneContact(model.convertDataToContact(oldContactData), model.convertDataToContact(newContactData));
			enableControlMainView();
			view.getTable().setModel(new TableModel(model.getAllContacts()));
		}
		chooseDataPanel("table");
		
	}

	@Override
	public void deleteContact(Object[] contact) {
		model.deleteOneContact(model.convertDataToContact(contact));
		enableControlMainView();
		view.getTable().setModel(new TableModel(model.getAllContacts()));
		chooseDataPanel("table");
	}

	@Override
	public void closeApp() {
		model.saveAddressBook();
	}
	
//	private void chooseAppView(String panelName) {
//		JPanel cards = ((AppView) view).getDataPanel();
//		CardLayout cl = ((CardLayout) cards.getLayout());
//		cl.show(cards, panelName);
//	}
//
//	@Override
//	public void register(String username, String password, String confirmPassWord) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void login(String username, String password) {
//		// TODO Auto-generated method stub
//		
//	}


}
