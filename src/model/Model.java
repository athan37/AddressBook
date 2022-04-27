package model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Multiple tree to index all fields
 * @author DucAnh2
 *
 */
public class Model implements IModel {
	private TableModel tableModel;
	private List<Trie<Contact>> trees;
	
	public Model(List<Contact> contacts) {
		trees = new ArrayList<Trie<Contact>>(Arrays.asList(
					new Trie<Contact>(contacts, e -> e.getFirstName(), e -> e.getNumber()),
					new Trie<Contact>(contacts, e -> e.getLastName(), e -> e.getNumber()),
					new Trie<Contact>(contacts, e -> e.getNumber(), e -> e.getNumber()),
					new Trie<Contact>(contacts, e -> e.getEmail(), e -> e.getNumber())
				));
		tableModel = new TableModel(getAllContacts());
	}
	
	public void addContact(Contact contact) {
		trees.stream().forEach(tree -> tree.insert(contact));
	}
	
	public void updateOneContact(Contact contact, Contact newContact) {
		trees.stream().forEach(tree -> tree.update(contact, newContact));
//		tableModel.setData(this.getAllContacts()); Not yet
	}
	
	
	public void deleteOneContact(Contact contact) {
		trees.stream().forEach(tree -> tree.delete(contact));
	}
	
	public List<Contact> search(String string) {
		if (string.length() == 0) {
			return getAllContacts();
		}
		return trees.stream()
				.map(tree -> tree.search(string))
				.reduce(new ArrayList<>(), (result, subresult) -> { result.addAll(subresult); return result;}
		);
	}
	
	@Override
	public List<Contact> getAllContacts() {
		return trees.get(0).toList();
	}

	@Override
	public int getTotalContacts() {
		// TODO Auto-generated method stub
		return 0;
	}

	/**
	 * @return the tableModel
	 */
	public TableModel getTableModel() {
		return tableModel;
	}

//	@Override
//	public Contact generateContactFromData(
//			String firstName, 
//			String lastName, 
//			Gender gender, 
//			String number,
//			int age, 
//			String email,
//			Date date
//		) {
//			
//		return new Contact(firstName, lastName, Gender.M, number, age, email, date);
//	}
	
	@Override
	public Contact convertDataToContact(Object[] contactData) {
		return new Contact(
					(String) contactData[0],
					(String) contactData[1],
					(Gender) contactData[2],
					(String) contactData[3],
					(Integer) contactData[4],
					(String) contactData[5] 
		);
	}


}
