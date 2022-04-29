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

public abstract class AbstractViewContactPanel extends JPanel implements IView {
	
	//Fields
	protected JTextField firstNameField;
	protected JTextField lastNameField;
	protected JTextField emailField;
	protected JTextField ageField;
	protected JTextField numberField;
	protected JComboBox<Gender> genderField;
	protected IController controller;
	
	//Buttons
	protected JButton mainButton;
	
	//Abstract method to set up main button
	protected abstract void setUpMainButton();
	
	public AbstractViewContactPanel(IController controller, String firstName, String lastName, 
			Gender gender, String number, int age, String email) {
		
        this.controller =  controller;
		this.setSize(new Dimension(800, 800));
        setLayout(new MigLayout("", "[204px][80px][516px,grow][300px][75px]", "[25px][25px][25px][25px][25px][][25px][25px][25px][80px]"));
        
        JLabel label = new JLabel("Update person");
        label.setHorizontalAlignment(SwingConstants.CENTER);
        this.add(label, "cell 0 0 3 1,grow");
        
        JLabel label_5 = new JLabel("");
        add(label_5, "cell 0 1 3 1,grow");
        
        JLabel label_1 = new JLabel("First Name");
        this.add(label_1, "cell 0 2,grow");
        this.firstNameField = new JTextField();
        this.add(firstNameField, "cell 2 2,grow");
        this.firstNameField.setText(firstName);
        
        JLabel label_1_1 = new JLabel("Last Name");
        add(label_1_1, "cell 0 3");
        lastNameField = new JTextField();
        add(lastNameField, "cell 2 3,growx");
        this.lastNameField.setText(lastName);
        
        JLabel lblNewLabel = new JLabel("Gender");
        add(lblNewLabel, "cell 0 4");
        
        genderField = new JComboBox<Gender>(new Gender[]{Gender.M, Gender.F});
        add(genderField, "cell 2 4,growx");
        
        JLabel label_4 = new JLabel("Phone Number");
        this.add(label_4, "cell 0 5,grow");
        this.numberField = new JTextField();
        this.add(numberField, "cell 2 5,grow");
        this.numberField.setText(number);
        
        JLabel label_3 = new JLabel("Age");
        this.add(label_3, "cell 0 6,grow");
        this.ageField = new JTextField();
        this.add(ageField, "cell 2 6,grow");
        this.ageField.setText(age > 0 ? String.valueOf(age) : "");
        JLabel label_2 = new JLabel("Email");
        this.add(label_2, "cell 0 7,grow");
        
        this.emailField = new JTextField();
        this.add(emailField, "flowy,cell 2 7,grow");
        this.emailField.setText(email);
        
        
		setUpMainButton(); //Set up main button depending on the class, set it up before putting it in the frame
        this.add(mainButton, "cell 3 9,alignx left,aligny center");
        this.setVisible(true);
	}

	@Override
	public JTable getTable() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public void addFeatures(Features features) {
		// TODO Auto-generated method stub
		
	}
}
		

