import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import model.Contact;
import model.Gender;
import model.tree.Trie;

public class TrieTest {
	private Trie<Contact> tree;
	private List<Contact> data;
	
	@Before
	public void setUp() {
		Contact contact1 = new Contact("Kathy", "Smith", Gender.F, "0123456789", 18, "saf@asdc.com");
		Contact contact2 = new Contact("Larson", "Tan", Gender.F, "06743109893", 18, "massey.sharp@gmail.com");
		Contact contact3 = new Contact("Kirk", "Ray", Gender.F, "05155382108", 59, "hauser.deal@gmail.com");
		Contact contact4 = new Contact("Morgan", "Bynum", Gender.M, "04109878962", 17, "myers.wallace@gmail.com");
		Contact contact5 = new Contact("Khan", "Marks", Gender.F, "0312731928", 12, "helms.greenberg@gmail.com");
		
		data = new ArrayList<Contact>(Arrays.asList(contact1, contact2, contact3, contact4, contact5));
		tree = new Trie<Contact>(e -> e.getFirstName(), e -> e.getNumber());
	}
	
	@Test
	public void testToList() {
		tree.insert(data.get(0));
		tree.insert(data.get(1));
		tree.insert(data.get(2));
		tree.insert(data.get(3));
		
		String expected = "["
				+ "Contact [firstName=Kathy, lastName=Smith, gender=Female, number=0123456789, age=18, email=saf@asdc.com], "
				+ "Contact [firstName=Kirk, lastName=Ray, gender=Female, number=05155382108, age=59, email=hauser.deal@gmail.com], "
				+ "Contact [firstName=Larson, lastName=Tan, gender=Female, number=06743109893, age=18, email=massey.sharp@gmail.com], "
				+ "Contact [firstName=Morgan, lastName=Bynum, gender=Male, number=04109878962, age=17, email=myers.wallace@gmail.com]"
				+ "]";
		assertEquals(tree.toList().toString(), expected);
	}
	
	@Test
	public void testSearch() {
		tree.insert(data.get(0));
		tree.insert(data.get(1));
		tree.insert(data.get(2));
		tree.insert(data.get(3));
	}
	
	@Test
	public void testOneInsert() {
		tree.insert(data.get(0));
		String expected = "[Contact [firstName=Kathy, lastName=Smith, gender=Female, number=0123456789, age=18, email=saf@asdc.com]]";
		assertEquals(tree.toList().toString(), expected);
	}
	
	@Test
	public void testDelete() {
		tree.insert(data.get(0));
		tree.insert(data.get(2));
		tree.insert(data.get(4));
		
		String expected = "["
				+ "Contact [firstName=Kathy, lastName=Smith, gender=Female, number=0123456789, age=18, email=saf@asdc.com], "
				+ "Contact [firstName=Khan, lastName=Marks, gender=Female, number=0312731928, age=12, email=helms.greenberg@gmail.com], "
				+ "Contact [firstName=Kirk, lastName=Ray, gender=Female, number=05155382108, age=59, email=hauser.deal@gmail.com]"
				+ "]";
		
		assertEquals(tree.search("K").toString(), expected);
	}
	
	@Test
	public void testMultipleInsert() {
		tree.insert(data.get(0));
		tree.insert(data.get(1));
		tree.insert(data.get(2));
		
		// Based on lexicographic order of first name
		String expected = "["
				+ "Contact [firstName=Kathy, lastName=Smith, gender=Female, number=0123456789, age=18, email=saf@asdc.com], "
				+ "Contact [firstName=Kirk, lastName=Ray, gender=Female, number=05155382108, age=59, email=hauser.deal@gmail.com], "
				+ "Contact [firstName=Larson, lastName=Tan, gender=Female, number=06743109893, age=18, email=massey.sharp@gmail.com]"
				+ "]";
		assertEquals(tree.toList().toString(), expected);
	}
	
	@Test
	public void testInsertandDelete() {
		tree.insert(data.get(0));
		tree.insert(data.get(1));
		tree.delete(data.get(1));
		
		String expected = "["
				+ "Contact [firstName=Kathy, lastName=Smith, gender=Female, number=0123456789, age=18, email=saf@asdc.com]"
				+ "]";
		assertEquals(tree.toList().toString(), expected);
	}
	
	@Test
	public void testUpdate() {
		tree.insert(data.get(0));
		tree.insert(data.get(1));
		tree.insert(data.get(2));
		
		Contact newContact = new Contact(
					"Kirky",
					data.get(2).getLastName(),
					data.get(2).getGender(),
					data.get(2).getNumber(),
					data.get(2).getAge(),
					data.get(2).getEmail());
					
		tree.update(data.get(2), newContact);
		
		// Based on lexicographic order of first name
		String expected = "["
				+ "Contact [firstName=Kathy, lastName=Smith, gender=Female, number=0123456789, age=18, email=saf@asdc.com], "
				+ "Contact [firstName=Kirky, lastName=Ray, gender=Female, number=05155382108, age=59, email=hauser.deal@gmail.com], "
				+ "Contact [firstName=Larson, lastName=Tan, gender=Female, number=06743109893, age=18, email=massey.sharp@gmail.com]"
				+ "]";
		assertEquals(expected, tree.toList().toString());
	}

}
