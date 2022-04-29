package view;

import java.awt.CardLayout;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;

import controller.Controller;
import controller.IController;
import model.Contact;
import model.Gender;
import model.IModel;
import model.Model;

public class AppView extends JFrame implements IView {

	private MainView mainView;
	private JPanel loginView;
	private JPanel registerView;
	private JPanel appView;
	
	private IModel model;
	private IController controller;
	private IView view;
	
	public AppView() {
		initComponents();
		
		setSize(new Dimension(800, 500));
		
		appView   = new JPanel(new CardLayout());
		loginView = new LoginView(controller);
		registerView = new RegisterView();
		mainView  = new MainView(model);
		
		//Add panels
		this.getContentPane().add(appView);
		appView.add(loginView, "login");
		appView.add(mainView, "main");
		appView.add(registerView, "register");
		
		
	}
	
	
	private List<Contact> loadContacts() {
		return new ArrayList<>(Arrays.asList(
			new Contact("Kathy", "Smith", Gender.F, "0123456789", 18, "saf@asdc.com"),
			new Contact("Strong", "Smile", Gender.M, "0123456789", 18, "saf@asdc.com"),
			new Contact("Strenth", "Smile", Gender.M, "0123456789", 18, "saf@asdc.com"),
			new Contact("Manny", "Smile", Gender.M, "0123456789", 18, "saf@asdc.com"),
			new Contact("Menny", "Rigge", Gender.F, "0123456789", 18, "saf@asdc.com")
		));
	}
	
	private void initComponents() {
		view  = new RegisterView();
		model = new Model(loadContacts());
		controller = new Controller(model, view);
	}
	
	
	public static void main(String[] args) {
		new AppView();
	}
}
