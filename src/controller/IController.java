package controller;

import model.TableModel;

public interface IController {
	public void search(String prefix);

	/**
	 * View the current data of the selected contact
	 * @param contactData the data of the table row
	 */
	public void viewSelectedContact(Object[] contactData);
	
	/**
	 * View a new contact to help insert it 
	 */
	public void viewNewContact();
	
	/**
	 * Update a contact in the phone book
	 * @param oldContactData old contact to be updated
	 * @param newContactData new contact 
	 */
	public void updateContact(Object[] oldContactData, Object[] newContactData);

	
	/**
	 * Insert a new contact to the phone book
	 * @param newContactData new contact to be inserted
	 */
	public void addContact(Object[] newContactData);
	
	/**
	 * Delete the contact with the given information
	 * @param contact the contact to be deleted
	 */
	public void deleteContact(Object[] contact);
	
	public void register(String username, String password, String confirmPassWord);
	
	public void login(String username, String password);
	
	public TableModel getContactTableData();

}
