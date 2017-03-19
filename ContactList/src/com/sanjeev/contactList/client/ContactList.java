package com.sanjeev.contactList.client;

import com.sanjeev.contactList.client.presenter.ContactPresenter;
import com.sanjeev.contactList.client.presenter.ContactPresenter.Display;
import com.sanjeev.contactList.client.presenter.Presenter;
import com.sanjeev.contactList.client.view.Contactlistview;
import com.sanjeev.contactList.shared.FieldVerifier;
import com.sanjeev.contactList.shared.model.Contact;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
//checking the update
public class ContactList implements EntryPoint {
	Contact dummyCon;
	public void onModuleLoad() {
		dummyCon = new Contact("Sanjeev", "kumar","c",5, "e","d", "f");
		Display view = new Contactlistview();;
		Presenter presenter= new ContactPresenter(view, dummyCon);;
		presenter.go(RootPanel.get());
		
	}
}
