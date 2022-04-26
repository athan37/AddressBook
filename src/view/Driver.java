package view;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import controller.*;
import model.Contact;
import model.Gender;
import model.IModel;
import model.Model;

public class Driver {
	public static void main(String[] args) {
		List<Contact> contacts = new ArrayList<>(Arrays.asList(
			new Contact("Kathy", "Smith", Gender.F, "0123456789", 18, "saf@asdc.com", new Date()),
			new Contact("Strong", "Smile", Gender.M, "0123456789", 18, "saf@asdc.com", new Date()),
			new Contact("Strenth", "Smile", Gender.M, "0123456789", 18, "saf@asdc.com", new Date()),
			new Contact("Manny", "Smile", Gender.M, "0123456789", 18, "saf@asdc.com", new Date()),
			new Contact("Menny", "Rigge", Gender.F, "0123456789", 18, "saf@asdc.com", new Date())
		));
		
		
		IView view = new ContactViewImp();
		view.setVisible(false);
	}
}
