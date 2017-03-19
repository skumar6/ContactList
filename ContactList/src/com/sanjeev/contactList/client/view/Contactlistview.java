package com.sanjeev.contactList.client.view;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.sanjeev.contactList.client.presenter.ContactPresenter;
import com.sanjeev.contactList.client.presenter.ContactPresenter.Display;
import com.sanjeev.contactList.shared.model.Contact;


public class Contactlistview extends Composite implements Display{
	
	private Label label;
	private Button button;
	private ContactPresenter presenter;
	
	
	VerticalPanel vpanel = new VerticalPanel();
	CellTable<Contact> table ;
	DialogBox box;
	DialogBoxView d;
	public TableWidget  t;
	
	public Contactlistview() {
		initWidget(this.vpanel);	
		label = new Label("Contact List");
		label.setStyleName("gwt-Label");
		HorizontalPanel hpanel = new HorizontalPanel();
		t= new TableWidget(this);
		table = t.getTable();
		table.setVisible(true);
		button =  new Button("Add Contact", new ClickHandler() {
	        public void onClick(ClickEvent event) {
	        	addContact();
	          }
	    });
		hpanel.add(label);
		hpanel.add(button);
		hpanel.setCellHorizontalAlignment(button, HasHorizontalAlignment.ALIGN_RIGHT);
		hpanel.setWidth("100%");
		
		vpanel.add(hpanel);
		vpanel.add(table);	
	}

	@Override
	public void setPresenter(ContactPresenter contactPresenter) {
		this.presenter = contactPresenter;
	}

	@Override
	public void addContact() {
		d = new DialogBoxView(this);
    	box = d.getBox();
    	box.center();
	}
}
