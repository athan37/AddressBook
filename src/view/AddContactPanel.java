package view;

import javax.swing.JButton;

import controller.IController;
import model.Gender;

public class AddContactPanel extends AbstractViewContactPanel {

	public AddContactPanel(IController controller) {
		super(controller, "", "", Gender.M, "", 0, "");
	}

	@Override
	protected void setUpMainButton() {
		mainButton = new JButton("Add Contact");
        mainButton.addActionListener(e -> controller.addContact(
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
	}
	
	
	
}
