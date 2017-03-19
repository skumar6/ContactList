package com.sanjeev.contactList.client.presenter;

import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.Widget;
import com.sanjeev.contactList.shared.model.Contact;


public class ContactPresenter implements Presenter{
	 Display view;
	    Contact contact;
	    
	    public ContactPresenter(Display view,Contact contact){
	    	this.view = view;
	    	this.contact = contact;
	    	bind();
	    }
	    
	    public interface Display{
	    	public Widget asWidget();
	    	public void setPresenter(ContactPresenter contactPresenter);
			void addContact();
	    }
	    
		@Override
		public void bind() {	
			view.setPresenter(this);
		}

		@Override
		public void go(Panel panel) {
			panel.add(view.asWidget());
		}

		public Contact populateTable() {
			return contact;
		}
}
