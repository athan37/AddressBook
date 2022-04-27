package controller;


public interface IController {
	public void search(String prefix);

	public void viewContact(Object[] contactData);
	
	public void updateContact(Object[] oldContactData, Object[] newContactData);
}
