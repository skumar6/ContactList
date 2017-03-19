package com.sanjeev.contactList.client.view;

import java.util.Arrays;
import java.util.List;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.sanjeev.contactList.shared.model.Contact;


public class DialogBoxView {
	private DialogBox d;
	private TextBox namebox;
	private TextBox jobTitleBox;
	private TextBox NickNameBox;
	private ListBox ageBox;
	private ListBox GroupBox;
	private CheckBox isManager;
	private Boolean isEdit;
	Contactlistview view;
	private int ReplaceIndex;
	private String[] groupStr = {"Engineering", "Finance", "Front Office",
								  "IT", "Management", "Marketing", "Sales"};
	private List<String> groupList = Arrays.asList(groupStr);
	List<Contact> CONTACTS;
	
	public DialogBoxView(Contactlistview view){
		this.view = view;
		this.isEdit=false;
		d = new DialogBox();
		namebox = new TextBox();
		jobTitleBox = new TextBox();
		NickNameBox = new TextBox();
		ageBox = new ListBox ();
		for(int i =15; i<=100; i++){
			ageBox.addItem(i+"");
		}
		GroupBox = new ListBox ();
		for(String s : groupList){
			GroupBox.addItem(s);
		}	
	    isManager = new CheckBox(); 
		
	}
	
	public void showPrevEntries(Contact c){
		this.namebox.setValue(c.getFname()+" "+c.getLname());
		this.jobTitleBox.setValue(c.getJobTitle());
		this.ageBox.setSelectedIndex(Integer.valueOf(c.getAge()-15));
		this.NickNameBox.setValue(c.getNname());
		this.GroupBox.setSelectedIndex(groupList.indexOf(c.getGroup()));
		this.isManager.setValue(c.getIsManager().equals("yes")? true:false);
	}
	
	public DialogBox getBox(){	 
	   	 d.setText("Add Contact");
	   	 d.setGlassEnabled(true);
	   	 d.setAnimationEnabled(true);
	   	 
	   	 VerticalPanel hbox = new VerticalPanel();
	   	 hbox.add(new Label("name"));
	   	 hbox.add(namebox);
	   	 
	   	 hbox.add(new Label("Job title"));
	   	 hbox.add(jobTitleBox);
	   	 
	   	 hbox.add(new Label("age"));
	   	 hbox.add(ageBox);
	   	 
	   	 hbox.add(new Label("NickName"));
	   	 hbox.add(NickNameBox);
	   	 
	   	 hbox.add(new Label("Group"));
	   	 hbox.add(GroupBox);
	   	 
	   	 hbox.add(new Label("Manger"));
	   	 hbox.add(isManager);
	   	 
	   	 HorizontalPanel htemp = new HorizontalPanel();
	   	 htemp.add(new Button("Add", new ClickHandler() {
		        public void onClick(ClickEvent event) {
		        	if(isEdit){
		        		isEdit=!isEdit;
		        		TableWidget.CONTACTS.remove(ReplaceIndex);
		        	}
		        	callPresenter();
		        }		
		          }));
	   	 Button btn = new Button("Cancel", new ClickHandler() {
		        public void onClick(ClickEvent event) {
		        	removeBox();
		        }
		          });
	   	htemp.add(btn);
		htemp.setWidth("100%");
	   	hbox.add(htemp);
	   	 
	   	 hbox.setWidth(Integer.toString(Window.getClientWidth()-600)+ "px");
	   	 hbox.setHeight("400px");
	   	 d.add(hbox);
		 return d;
	}
	
	private void callPresenter() {
		StringBuilder sb = new StringBuilder();
		String name = namebox.getText(); // provide space seperated first and last name 
		String[] flnames = name.trim().split(" "); 
		String jobTitle = jobTitleBox.getText();
		String age = ageBox.getSelectedValue();
		int intage = Integer.parseInt(age);
		String nname = NickNameBox.getText();
		String group = GroupBox.getSelectedValue();
		String manager = isManager.getValue() ? "yes":"no";
		if(flnames.length<2){
			sb.append("Name must not be Blank");
		}
		if(jobTitle==null || jobTitle.length()==0){
			if(sb.length()!=0)
				sb.append("\n");
			sb.append("Job title must not be blank");
		}
		String msg = sb.toString();
		if(sb.length() != 0){
			Window.alert("Error \n"+msg);
			return;
		}
		Contact c = new Contact(flnames[0], flnames[1], jobTitle, intage, nname, group, manager);
		TableWidget tw = view.t;
	    tw.populateList(c);
	    tw.populateTable();
		d.hide();
	}
	
	private void removeBox() {
		d.hide();
	}

	public Boolean getIsEdit() {
		return isEdit;
	}

	public void setIsEdit(Boolean isEdit) {
		this.isEdit = isEdit;
	}

	public int getReplaceIndex() {
		return ReplaceIndex;
	}

	public void setReplaceIndex(int replaceIndex) {
		this.ReplaceIndex = replaceIndex;
	}
}
