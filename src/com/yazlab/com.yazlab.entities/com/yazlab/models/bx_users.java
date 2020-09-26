package com.yazlab.models;

public class bx_users {

	private int user_id;
	private String location;
	private String age;
	private String kullanici_adi;
	private String password;
	
	
	public bx_users() {
		super();
	}


	public bx_users(int user_id, String location, String age, String kullanici_adi, String password) {
		super();
		this.user_id = user_id;
		this.location = location;
		this.age = age;
		this.kullanici_adi = kullanici_adi;
		this.password = password;
	}


	public int getUser_id() {
		return user_id;
	}


	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}


	public String getLocation() {
		return location;
	}


	public void setLocation(String location) {
		this.location = location;
	}


	public String getAge() {
		return age;
	}


	public void setAge(String age) {
		this.age = age;
	}


	public String getKullanici_adi() {
		return kullanici_adi;
	}


	public void setKullanici_adi(String kullanici_adi) {
		this.kullanici_adi = kullanici_adi;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
