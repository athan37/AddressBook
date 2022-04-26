package controller;
import java.util.List;
import java.util.function.Predicate;

import model.Contact;

public interface NotFeatures {
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
	public List<Contact> findContact(Predicate<Contact> predicate);
	
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
}
