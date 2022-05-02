package view;

import java.awt.Dimension;


import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;


import controller.IController;
import model.Gender;

import net.miginfocom.swing.MigLayout;
import javax.swing.JButton;

/**
 * This class represent the input form to edit or add a contact
 * @author Duc Anh
 *
 */
public abstract class AbstractForm extends JPanel implements IView {
	
	//Fields
	protected JTextField firstNameField;
	protected JTextField lastNameField;
	protected JTextField emailField;
	protected JTextField ageField;
	protected JTextField numberField;
	protected JComboBox<Gender> genderField;
	
	//Labels
	protected JLabel firstNameLabel;
	protected JLabel lastNameLabel;
	protected JLabel emailLabel;
	protected JLabel ageLabel;
	protected JLabel genderLabel;
	protected JLabel numberLabel;
	protected JLabel updateLabel;
	
	protected IController controller;
	
	//Buttons
	protected JButton mainButton;
	
	//Abstract method to set up main button
	protected abstract void setUpMainButton();
	
	@Override
	public void initComponents() {
		//Init fields
        this.firstNameField = new JTextField();
        this.lastNameField  = new JTextField();
        this.numberField    = new JTextField();
        this.emailField     = new JTextField();
        this.ageField       = new JTextField();
        this.genderField    = new JComboBox<Gender>(new Gender[]{Gender.M, Gender.F});
        
        //Init labels
        this.firstNameLabel = new JLabel("First Name");
        this.lastNameLabel  = new JLabel("Last Name");
        this.genderLabel    = new JLabel("Gender");
        this.ageLabel       = new JLabel("Age");
        this.numberLabel    = new JLabel("Phone Number");
        this.emailLabel     = new JLabel("Email");
        this.updateLabel    = new JLabel("Update person");
	}
	
	public AbstractForm(IController controller, String firstName, String lastName, 
			Gender gender, String number, int age, String email) {
		initComponents();
        this.controller =  controller;
		this.setSize(new Dimension(800, 800));
        this.setLayout(new MigLayout("", "[204px][80px][516px,grow][300px][75px]", "[25px][25px][25px][25px][25px][][25px][25px][25px][80px]"));
        
        this.updateLabel.setHorizontalAlignment(SwingConstants.CENTER);
        this.add(updateLabel, "cell 0 0 3 1,grow");
        
        //Add fields & labels
        this.add(firstNameLabel, "cell 0 2,grow");
        this.add(firstNameField, "cell 2 2,grow");
        
        this.add(lastNameLabel, "cell 0 3");
        this.add(lastNameField, "cell 2 3,growx");
        
        this.add(genderLabel, "cell 0 4");
        this.add(genderField, "cell 2 4,growx");
        
        this.add(numberLabel, "cell 0 5,grow");
        this.add(numberField, "cell 2 5,grow");
        
        this.add(ageLabel, "cell 0 6,grow");
        this.add(ageField, "cell 2 6,grow");
        
        this.add(emailLabel, "cell 0 7,grow");
        this.add(emailField, "flowy,cell 2 7,grow");
        
        //Set data
        this.firstNameField.setText(firstName);
        this.lastNameField.setText(lastName);
        this.numberField.setText(number);
        this.ageField.setText(age > 0 ? String.valueOf(age) : "");
        this.emailField.setText(email);
        
		setUpMainButton(); //Set up main button depending on the class, set it up before putting it in the frame
        this.add(mainButton, "cell 3 9,alignx left,aligny center");
        this.setVisible(true);
	}
}
		

