package com.sanjeev.contactList.client.view;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.google.gwt.cell.client.ButtonCell;
import com.google.gwt.cell.client.EditTextCell;
import com.google.gwt.cell.client.FieldUpdater;
import com.google.gwt.cell.client.SafeHtmlCell;
import com.google.gwt.cell.client.TextCell;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.view.client.ListDataProvider;
import com.sanjeev.contactList.shared.model.Contact;


public class TableWidget {
	CellTable<Contact> table ;
	TextColumn<Contact> nameColumn ;
	List<Contact> list;
	public static List<Contact> CONTACTS;
	ListDataProvider<Contact> dataProvider;
	Contactlistview view;
	DialogBoxView d;
	DialogBox box;
		   
	public TableWidget(Contactlistview view){
		this.view = view;
		CONTACTS = new ArrayList<Contact>();
		table = new CellTable<Contact>();
		CONTACTS.add(new Contact("Joe", "Andretti", "Receptionist", 21, "Joe", "Front Office", "no"));
		CONTACTS.add(new Contact("Judy", "Cormac", "President", 52, "The boss", "Management", "yes"));
		CONTACTS.add(new Contact("Xavier", "Bigalow", "Team Lead", 28, "Old timer", "Engineering", "yes"));
		prepareTable();
		populateTable();
	}
	
	public CellTable<Contact> getTable(){
		return this.table;
	}	
	
	public void prepareTable(){

	    Column<Contact, SafeHtml> nameColumn = new Column<Contact, SafeHtml>(new SafeHtmlCell()) {
	      @Override
	      public SafeHtml getValue(Contact contact) {
	    	  SafeHtmlBuilder builder = new SafeHtmlBuilder();
	    	  builder.appendHtmlConstant(contact.getFname()+" "+contact.getLname() +"<br/>"+ contact.getJobTitle());
	    	  return builder.toSafeHtml();
	      }
	    };  
	    nameColumn.setSortable(true);
	 
	    Column<Contact, String> ageColumn = new Column<Contact, String>(new TextCell()) {
		      @Override
		      public String getValue(Contact contact) {
		        return contact.getAge()+"";
		      }
		};
		TextColumn<Contact> nnameColumn = new TextColumn<Contact>() {
			      @Override
			      public String getValue(Contact contact) {
			        return contact.getNname();
			      }
	    };
	    TextColumn<Contact> jtitleColumn = new TextColumn<Contact>() {
			@Override
		    public String getValue(Contact contact) {
				  return contact.getNname();
		    }
	    };
	    
	   Column<Contact, String> deleteBtn = new Column<Contact, String>(new ButtonCell())  {
	    	@Override
	        public String getValue(Contact contact)  {
	    	   return "Delete";
	    	}
	   };
	   deleteBtn.setFieldUpdater(new FieldUpdater<Contact, String>() {
		   @Override
	       public void update(int index, Contact contact, String s ) {
			   dataProvider.getList().remove(contact);
			   CONTACTS.remove(contact);
			   dataProvider.refresh();
			   table.redraw();
	    	}	   
	    });
	   
	   Column<Contact, String> editColumn = new Column<Contact, String>(new ButtonCell())  {
	 	   @Override
	 	   public String getValue(Contact contact)  {
	 	      return "Edit";
	 	   }
	 	};
	 	editColumn.setFieldUpdater(new FieldUpdater<Contact, String>() {
			   @Override
		       public void update(int index, Contact contact, String s ) {
				    d = new DialogBoxView(view);
				    d.showPrevEntries(contact);
				    d.setIsEdit(true);
				    d.setReplaceIndex(CONTACTS.indexOf(contact));;
			    	box = d.getBox();
			    	box.setText("Edit Contact");
			    	box.center();
			    	box.show();
		    	}	   
		    });
	 	
	   Column<Contact, String> groupColumn = new Column<Contact, String>(new TextCell()) {
		   @Override
		   public String getValue(Contact contact) {
		       return contact.getGroup()+"";
		   }
	    };
	  Column<Contact, String> managerColumn = new Column<Contact, String>(new TextCell()) {
		    @Override
			public String getValue(Contact contact) {
			    return contact.getIsManager()+"";
			}
	   }; 
	 // Add the columns
	  SafeHtmlBuilder nameHeaderBuilder = new SafeHtmlBuilder();
	  nameHeaderBuilder.appendHtmlConstant("Name<br/>Job Title");
	  table.addColumn(nameColumn,nameHeaderBuilder.toSafeHtml());
	  table.addColumn(ageColumn, "Age");
	  table.addColumn(nnameColumn, "NickName");
	  table.addColumn(groupColumn, "Group");
	  table.addColumn(managerColumn, "Manager");
	  table.addColumn(editColumn, "");
      table.addColumn(deleteBtn, ""); 
	  table.setWidth("100%", true);
    }
	
	public void populateTable(){
	    dataProvider = new ListDataProvider<Contact>();
        dataProvider.addDataDisplay(table);
        
        // Add the data to data provider,which automatically pushes it to the widget.
        list= dataProvider.getList();
        for(Contact c : CONTACTS){
        	list.add(c);
        }
        Collections.sort(list, new Comparator<Contact>() {
            @Override
            public int compare(Contact c1, Contact c2) {
            	  if (c1 == c2) {
                      return 0;
                    } 
                    // Compare the name columns.
                    if(c1!=null){
                    	if(c1.getLname().equals(c2.getLname())){
                    		return (c2 != null) ? c1.getFname().compareTo(c2.getFname()) : 1;
                    	}else{
                    		return (c2 != null) ? c1.getLname().compareTo(c2.getLname()) : 1;
                    	}
                    }
                    return -1;
                  }
         });
        dataProvider.refresh();
	}

	public void populateList(Contact c){
		CONTACTS.add(c);
	}
}
