package view;

import model.Contact;

public interface Features {
	void displayContacts();
	void updateContact(Contact oldContact, Contact newContact);
	void deleteContact(Contact contact);
	void search();
}
