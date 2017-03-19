package com.sanjeev.contactList.shared.model;

public class Contact {
	private String fname;
	private String lname;
	private int age;
	private String nname;
	private String jobTitle;
	private String isManager;
	private String group;
	
	public Contact(String fname, String lname, String jobTitle, int age, String nname, String group, String isManager ){
		this.fname = fname;
		this.lname = lname;
		this.jobTitle = jobTitle;
		this.age = age;
		this.nname = nname;
		this.group = group;
		this.isManager = isManager;
	}

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public String getLname() {
		return lname;
	}

	public void setLname(String lname) {
		this.lname = lname;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getNname() {
		return nname;
	}

	public void setNname(String nname) {
		this.nname = nname;
	}

	public String getJobTitle() {
		return jobTitle;
	}

	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}

	public String getIsManager() {
		return isManager;
	}

	public void setIsManager(String isManager) {
		this.isManager = isManager;
	}

	public String getGroup() {
		return group;
	}

	public void setGroup(String group) {
		this.group = group;
	}

}
