package model;
import java.util.Date;
import java.util.List;
import java.util.function.Predicate;

public interface IModel {
	/**
	 * This should return a list of matching contacts
	 * @param predicate criteria for finding the right contact
	 * @return
	 */
	public List<Contact> search(String prefix);
	
	/**
	 * This method adds one contact to the address book
	 * @param contact the contact to be added
	 */
	public void addOneContact(Contact contact);
	
	/**
	 * This method replaces the information in the original contact
	 * using the information form the newContact
	 * @param contact
	 * @param newContact
	 */
	public void updateOneContact(Contact originalContact, Contact newContact);
	/**
	 * Delete the contact by providing exact information
	 * @param contact
	 */
	public void deleteOneContact(Contact contact);
	
	/**
	 * Return the all contacts from the address book
	 * @return list of all contacts
	 */
	public List<Contact> getAllContacts();
	
	/**
	 * Generate contact from given information
	 * @return contact
	 */
	public Contact convertDataToContact(Object[] contactData);
	
	/**
	 * Get the current list of contacts base on the search state
	 * @param searchState the user input
	 * @param sortCriteria the criteria for sorting
	 * @return
	 */
	public List<Contact> getCurrentContacts(String searchState, String sortCriteria);
	
	/**
	 * Push all information from the address book to the database
	 */
	public void saveAddressBook();
	
	public TableModel getTableModel();


}
