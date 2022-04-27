package model;

import java.util.Date;

public class TrieDriver {
	public static void main(String[] args) {
		Contact contact1 = new Contact("Kathy", "Smith", Gender.F, "0123456789", 18, "saf@asdc.com");
		Contact contact2 = new Contact("Strong", "Smile", Gender.M, "0123456789", 18, "saf@asdc.com");
		Contact contact3 = new Contact("Strenth", "Smile", Gender.M, "0123456789", 18, "saf@asdc.com");
		Contact contact4 = new Contact("Manny", "Smile", Gender.M, "0123456789", 18, "saf@asdc.com");
		Contact contact5 = new Contact("Menny", "Rigge", Gender.F, "0123456789", 18, "saf@asdc.com");
		Trie<Contact> tree = new Trie<>(e -> e.getFirstName(), e -> e.getNumber());
		tree.insert(contact1);
		tree.insert(contact2);
		tree.insert(contact3);
		tree.insert(contact4);
		tree.insert(contact5);
		
		
		
		System.out.println("PRINT ALL");
		System.out.println(tree.toList().size());
//		System.out.println(tree.lookup("Strong", e -> e.getGender() == Gender.M));
//		System.out.println(tree.lookup("Strong", e -> e.getGender() == Gender.F));
		System.out.println("AFTER DELETE: ");
		tree.delete(contact2);
		System.out.println(tree.search("Str"));
		
		System.out.println("DELETE AND FIND KATHY: ");
		tree.delete(contact1);
		System.out.println(tree.search("Kathy"));
		
		System.out.println("DELETE MAN: ");
		tree.delete(contact3);
		System.out.println(tree.search("M"));
//		System.out.println(tree.lookup("Strong", e -> e.getGender() == Gender.M));
		
		System.out.println("DELETE Manny: ");
		tree.delete(contact4);
		System.out.println(tree.search("Manny"));
		System.out.println("FIND M AFTER DELETE Manny: ");
		System.out.println(tree.search("M"));
		
//		System.out.print(tree.lookup("Men"));
	}
}
