package model;

import java.util.Calendar;
import java.util.Date;

/**
 * Class containing information for a contact
 * @author DucAnh2
 *
 */
public class Contact {
	private String firstName;
	private String lastName;
	private Gender gender;
	private String number;
	private int age;
	private String email;
	private Calendar birthDate;
	
	/**
	 * Constructor for user
	 * @param firstName
	 * @param lastName
	 * @param gender
	 * @param number
	 * @param age
	 * @param email
	 */
	public Contact(String firstName, String lastName,
	 Gender gender, String number, int age,
	 String email) {
		this.firstName = firstName;
		this.lastName  = lastName;
		this.number    = number;
		this.gender    = gender;
		this.age       = age;
		this.email     = email;
	}

	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * @return the gender
	 */
	public Gender getGender() {
		return gender;
	}

	/**
	 * @param gender the gender to set
	 */
	public void setGender(Gender gender) {
		this.gender = gender;
	}

	/**
	 * @return the number
	 */
	public String getNumber() {
		return number;
	}

	/**
	 * @param number the number to set
	 */
	public void setNumber(String number) {
		this.number = number;
	}

	/**
	 * @return the age
	 */
	public int getAge() {
		return age;
	}

	/**
	 * @param age the age to set
	 */
	public void setAge(int age) {
		this.age = age;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "Contact [firstName=" + firstName + ", lastName=" + lastName + ", gender=" + gender + ", number="
				+ number + ", age=" + age + ", email=" + email + "]";
	}
	
	
}
