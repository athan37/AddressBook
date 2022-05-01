package model;

import java.util.Comparator;

/**
 * Factory class that allows us to generate instance base on a given string
 * The comparator class is already a functional interface, so there is 
 * no need to recreate another functional interface to make the factory pattern.
 * That's being said, we may even get rid of the comparator and just use lambda
 * expression instead of the functional interface. This class aim to demonstrate
 * the ability to use Comparator in java.
 * 
 * References: https://www.tutorialspoint.com/design_pattern/factory_pattern.htm
 * @author DucAnh2
 *
 */
public class ContactComparatorFactory {
	
	/**
	 * Generate comparator base on the given criteria
	 * @param criteria to generate comparator
	 * @return Comparator that can compare 2 objects based on 2 criteria
	 */
	public static Comparator<Contact> getComparatorBy(String criteria) {
		switch(criteria) {
			case "First Name":
				return new Comparator<Contact>() {
						@Override
						public int compare(Contact contact1, Contact contact2) {
						   return contact1.getFirstName().compareTo(contact2.getFirstName());
					   } 
					};
			case "Last Name":
				return new Comparator<Contact>() {
						@Override
						public int compare(Contact contact1, Contact contact2) {
						   return contact1.getLastName().compareTo(contact2.getLastName());
						}
				   };
			case "Age":
				return new Comparator<Contact>() {
						@Override
						public int compare(Contact contact1, Contact contact2) {
						   return Integer.compare(contact1.getAge(), contact2.getAge());
						}
				   };
			case "Email":
				return new Comparator<Contact>() {
						@Override
						public int compare(Contact contact1, Contact contact2) {
						   return contact1.getEmail().compareTo(contact2.getEmail());
						}
				   };
			case "Gender":
				return new Comparator<Contact>() {
						@Override
						public int compare(Contact contact1, Contact contact2) {
						   return contact1.getGender().compareTo(contact2.getGender());
						}
				   };
			case "Number":
				return new Comparator<Contact>() {
						@Override
						public int compare(Contact contact1, Contact contact2) {
						   return contact1.getNumber().compareTo(contact2.getNumber());
						}
				   };
			}
			
			//Default return, this comparator does nothing 
			return new Comparator<Contact>() {
					@Override
					public int compare(Contact contact1, Contact contact2) {
					   return 0;
					}
		   };
	}
}
