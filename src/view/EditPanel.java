package view;

import java.awt.Dimension;

import java.awt.GridLayout;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import org.jdatepicker.JDatePanel;
import org.jdatepicker.JDatePicker;
import org.jdatepicker.UtilCalendarModel;

import java.awt.FlowLayout;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;

import controller.Controller;
import controller.IController;
import model.Gender;
import model.IModel;

import java.awt.CardLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.SpringLayout;
import net.miginfocom.swing.MigLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.awt.event.ActionEvent;

public class EditPanel extends JPanel implements IView {
	private JTextField firstNameField;
	private JTextField lastNameField;
	private JTextField emailField;
	private JTextField ageField;
	private JTextField numberField;
	private JComboBox<Gender> genderField;
	private JDatePicker datePicker;
	
	public EditPanel(IController controller, String firstName, String lastName, 
			Gender gender, String number, int age, String email) {
//        final JOptionPane optionPane = new JOptionPane(
//                "The only way to close this dialog is by\n"
//                + "pressing one of the following buttons.\n"
//                + "Do you understand?",
//                JOptionPane.QUESTION_MESSAGE,
//                JOptionPane.YES_NO_OPTION);
//        this.add(optionPane);
		
        
		this.setSize(new Dimension(800, 800));
        setLayout(new MigLayout("", "[204px][80px][516px,grow][200px]", "[25px][25px][25px][25px][25px][][25px][25px][25px][80px]"));
        
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
        
        JButton updateBtn = new JButton("Update Btn");
        
        updateBtn.addActionListener(e -> controller.updateContact(
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
        
        this.add(updateBtn, "cell 3 9,alignx left,aligny center");
        this.setVisible(true);
        
        /**
         * How to have date picker in java swing:
         * https://stackoverflow.com/questions/26323390/date-picker-gui-component-for-java-swing
         * Source code:
         * https://github.com/JDatePicker/JDatePicker/blob/master/src/main/java/org/jdatepicker/JDatePicker.java
         */
//        JLabel lblNewLabel_1 = new JLabel("Birth date");
//        this.add(lblNewLabel_1, "cell 0 8");
//        datePicker = new JDatePicker();
//        this.datePicker.getModel().setDate(
//        		date.getTime().getYear(),
//        		date.getTime().getMonth(),
//        		date.getTime().getDay(),
////        		getDataFromDate(date, Calendar.YEAR), 
////        		getDataFromDate(date, Calendar.MONTH), 
////        		getDataFromDate(date, Calendar.DAY_OF_MONTH)
//        		);
//        this.add(datePicker, "cell 2 8");
        
	}
	
	private int getDataFromDate(Date date, int constant) {
		Calendar cal = Calendar.getInstance();
        cal.setTime(date);
		return cal.get(constant);
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
	
	public static void main(String[] args) {
		JFrame jFrame = new JFrame();
		jFrame.setSize(new Dimension(800, 800));
		
		jFrame.getContentPane().add(new EditPanel(new Controller(null, null), "", "", Gender.M, "", 0, ""));
		
		jFrame.pack();
		
		jFrame.setVisible(true);
	}
}
		
