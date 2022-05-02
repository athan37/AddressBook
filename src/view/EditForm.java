package view;

import javax.swing.JButton;

import controller.IController;
import model.Gender;

/**
 * Class represents the form filled with the data from 
 * the selected row from the table
 * 
 * @author Duc Anh
 *
 */
public class EditForm extends AbstractForm {
	private String firstName;
	private String lastName;
	private Gender gender;
	private String number;
	private int age;
	private String email;
	
	public EditForm(IController controller, String firstName, String lastName, Gender gender, String number,
			int age, String email) {
		super(controller, firstName, lastName, gender, number, age, email);
		
		this.firstName = firstName;
		this.lastName = lastName;
		this.gender = gender;
		this.number = number;
		this.age = age;
		this.email = email;
	}

	@Override
	protected void setUpMainButton() {
		//Set up edit button
		mainButton = new JButton("Update Contact");
        mainButton.addActionListener(e -> controller.updateContact(
        		new Object[] { // Old data
						firstName,
						lastName,
						gender, //Add combo box here
						number, 
						age,
						email
						},
        		new Object[] {
        				//New data
						firstNameField.getText(),
						lastNameField.getText(),
						genderField.getSelectedItem(), //Add combo box
						numberField.getText(),
						ageField.getText().length() == 0 ? 0 : Integer.parseInt(ageField.getText()),
						emailField.getText()
						}
        		));
      //Set up additional delete button
       JButton deleteBtn = new JButton("Delete Contact");
	   this.add(deleteBtn, "cell 2 9,alignx left,aligny center");
	   deleteBtn.addActionListener(e -> controller.deleteContact(
        		new Object[] { // Old data
						firstName,
						lastName,
						gender, //Add combo box here
						number, 
						age,
						email
				}
	   ));
	}
	
	
	
}
