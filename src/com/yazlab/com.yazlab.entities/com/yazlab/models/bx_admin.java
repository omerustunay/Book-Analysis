package com.yazlab.models;

public class bx_admin {

	private int adminID;
	private String userName;
	private String password;
	private String age;
	private String location;
	
	public bx_admin() {
		super();
	}

	public bx_admin(int adminID, String userName, String password, String age, String location) {
		super();
		this.adminID = adminID;
		this.userName = userName;
		this.password = password;
		this.age = age;
		this.location = location;
	}

	public int getAdminID() {
		return adminID;
	}

	public void setAdminID(int adminID) {
		this.adminID = adminID;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}
	
	
}
