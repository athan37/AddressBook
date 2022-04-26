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
import java.awt.FlowLayout;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;
import java.awt.CardLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.SpringLayout;
import net.miginfocom.swing.MigLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.Date;
import java.awt.event.ActionEvent;

public class PopUp extends JPanel implements IView {
	private JTextField firstNameField;
	private JTextField lastNameField;
	private JTextField emailField;
	private JTextField ageField;
	private JTextField numberField;
	private JComboBox genderField;
	
	private String firstName;
	private String email;
	private String lastName;
	private int age;
	private String number;
	private Date date;
//	private JTextField birthDate;
	
	public PopUp(String firstName, String lastName, String email, int age, String number, Date date) {
//        final JOptionPane optionPane = new JOptionPane(
//                "The only way to close this dialog is by\n"
//                + "pressing one of the following buttons.\n"
//                + "Do you understand?",
//                JOptionPane.QUESTION_MESSAGE,
//                JOptionPane.YES_NO_OPTION);
//        this.add(optionPane);
		
		this.firstName = firstName;
        
		this.setSize(new Dimension(800, 800));
        setLayout(new MigLayout("", "[204px][80px][516px,grow]", "[25px][25px][25px][25px][25px][25px][25px][25px][80px]"));
        
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
        
        this.emailField = new JTextField();
        this.add(emailField, "flowy,cell 2 3,grow");
        this.firstNameField.setText(email);
        JLabel label_2 = new JLabel("Email");
        this.add(label_2, "cell 0 3 3 1,grow");
        
        JLabel label_4 = new JLabel("Phone Number");
        this.add(label_4, "cell 0 4,grow");
        this.numberField = new JTextField();
        this.add(numberField, "cell 2 4,grow");
        this.numberField.setText(number);
        
        JLabel label_3 = new JLabel("Age");
        this.add(label_3, "cell 0 5,grow");
        this.ageField = new JTextField();
        this.add(ageField, "cell 2 5,grow");
        this.ageField.setText(String.valueOf(age));
        
        
        JLabel label_1_1 = new JLabel("Last Name");
        add(label_1_1, "cell 0 6");
        lastNameField = new JTextField();
        add(lastNameField, "cell 2 6,growx");
        this.lastNameField.setText(lastName);
        
        JButton updateBtn = new JButton("Update Btn");
//        updateBtn.addActionListener(e -> );
        add(updateBtn, "cell 2 8,alignx right");
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
	
//	public static void main(String[] args) {
//		JFrame jFrame = new JFrame();
//		jFrame.setSize(new Dimension(800, 800));
//		
//		jFrame.getContentPane().add(new PopUp());
//		
//		jFrame.pack();
//		
//		jFrame.setVisible(true);
//	}
}
		
