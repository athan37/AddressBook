package controller;

import java.awt.CardLayout;


import javax.swing.JPanel;

import model.Gender;
import model.IModel;
import model.Model;
import model.TableModel;
import view.EditForm;
import view.IView;
import view.MainView;

public class Controller implements IController {
	private Model model;
	private MainView view;
	
	/**
	 * Constructor for the controller
	 * @param model
	 * @param view
	 */
	public Controller(IModel model, IView view) {
		this.model = (Model) model;
		this.view  = (MainView) view;
	}
	
	@Override
	public void searchAndSort(String prefix, String sortCriteria) {
		view.getTable().setModel(new TableModel(model.getCurrentContacts(prefix, sortCriteria)));
	}
	
	@Override
	public TableModel getContactTableData() {
		return model.getTableModel();
	}


	@Override
	public void viewSelectedContact(Object[] contactData) {
		disableControlMainView();
		view.setUpdatePanel(
				new EditForm(
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
		view.setUpdatePanel(new AddForm(this));
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
	
	//Helper methods
	/**
	 * This method helps choose panels base on their name
	 * for the data panel in the main screen 
	 * @param panelName the name of the panel
	 */
	private void chooseDataPanel(String panelName) {
		JPanel cards = view.getDataPanel();
		CardLayout cl = ((CardLayout) cards.getLayout());
		cl.show(cards, panelName);
	}
	
	/**
	 * This method is a helper to turn off the search 
	 * and the add button when the user is viewing data 
	 * of any contact
	 */
	private void disableControlMainView() {
		view.getTextField().setEnabled(false);
		view.getAddBtn().setEnabled(false);
		view.getSortSelect().setEnabled(false);
	}
	
	/**
	 * This method turns on the search 
	 * and the add button after the user viewed data 
	 * of any contact
	 */
	private void enableControlMainView() {
		view.getTextField().setEnabled(true);
		view.getTextField().setText("");
		view.getAddBtn().setEnabled(true);
		view.getSortSelect().setEnabled(true);
	}


}
