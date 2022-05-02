package model;

import java.util.ArrayList;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import model.tree.Trie;

/**
 * Model base on multiple prefix tree to index all fields
 * Search will aggregate the result from all the trees
 * @author DucAnh2
 *
 */
public class Model implements IModel {
	private TableModel tableModel;
	private List<Trie<Contact>> trees;
	private MySQLConnector connector;
	
	public Model() {
		List<Contact> contacts = new ArrayList<>();
		try {
			connector = new MySQLConnector();
			contacts = connector.loadContacts();
		} catch (Exception e) {
			System.out.println(e);
		}
		/*
		 * The number is unique, so we can make identifier function to get the number
		 * The other field can be the key, but not age and gender. They are not a good 
		 * field to index because it will make the trie more flat and more linear
		 * search
		 */
		trees = new ArrayList<Trie<Contact>>(Arrays.asList(
					new Trie<Contact>(contacts, e -> e.getFirstName(), e -> e.getNumber()),
					new Trie<Contact>(contacts, e -> e.getLastName(), e -> e.getNumber()),
					new Trie<Contact>(contacts, e -> e.getNumber(), e -> e.getNumber()),
					new Trie<Contact>(contacts, e -> e.getEmail(), e -> e.getNumber())
				));
		tableModel = new TableModel(getAllContacts());
	}
	
	@Override
	public void addOneContact(Contact contact) {
		trees.stream().forEach(tree -> tree.insert(contact));
	}
	
	@Override
	public void updateOneContact(Contact contact, Contact newContact) {
		trees.stream().forEach(tree -> tree.update(contact, newContact));
	}
	
	@Override
	public void deleteOneContact(Contact contact) {
		trees.stream().forEach(tree -> tree.delete(contact));
	}
	
	@Override
	public List<Contact> search(String string) {
		if (string.length() == 0) {
			return getAllContacts();
		}
		
		/*
		 * Aggregate search result from all the trees
		 * and select distinct result base on the number
		 * (Contact already implements Comparable<Contact>)
		 */
		return trees.stream()
				.map(tree -> tree.search(string))
				.reduce(new ArrayList<>(), (result, subresult) -> { result.addAll(subresult); return result;})
				.stream().distinct().collect(Collectors.toList()); // Select unique result only
	}
	
	@Override
	public List<Contact> getAllContacts() {
		return trees.get(0).toList();
	}

	/**
	 * @return the tableModel
	 */
	public TableModel getTableModel() {
		return tableModel;
	}

	@Override
	public Contact convertDataToContact(Object[] contactData) {
		return new Contact(
					(String) contactData[0],
					(String) contactData[1],
					(Gender) contactData[2],
					(String) contactData[3],
					(Integer)contactData[4],
					(String) contactData[5] 
		);
	}

	@Override
	public void saveAddressBook() {
		connector.writeContacts(getAllContacts());
	}

	@Override
	public List<Contact> getCurrentContacts(String searchState, String sortCriteria) {
		return search(searchState).stream()
				.sorted(ContactComparatorFactory.getComparatorBy(sortCriteria))
				.collect(Collectors.toList());
	}
}
