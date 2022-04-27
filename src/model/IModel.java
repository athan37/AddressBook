package model;
import java.util.Date;
import java.util.List;
import java.util.function.Predicate;

public interface IModel {
	/**
	 * Count the total number of contacts
	 * @return total number of contacts
	 */
	public int getTotalContacts();
	
	/**
	 * This should return a list of matching contacts
	 * @param predicate criteria for finding the right contact
	 * @return
	 */
	public List<Contact> search(String prefix);
	
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
	
	public TableModel getTableModel();


}
