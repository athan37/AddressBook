package controller;

import java.awt.event.ActionEvent;

import java.awt.event.ActionListener;
import java.util.List;
import java.util.function.Predicate;

import model.Contact;
import model.IModel;
import model.Model;
import model.TableModel;
import view.Features;
import view.IView;

public class Controller implements ActionListener, IController{
	private IModel model;
	private IView view;
	
	public Controller(IModel model, IView view) {
		this.model = model;
		this.view  = view;
	}
	
	@Override
	public void search(String prefix) {
		System.out.println(model.search("").size());
		view.getTable().setModel(new TableModel(model.search(prefix)));
	}
	
//	public void setView(IView v) {
//		view = v;
//		view.addFeatures(this);
//	}

	public List<Contact> findContact(Predicate<Contact> predicate) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		switch(e.getActionCommand()) {
			
		}
	}


}
