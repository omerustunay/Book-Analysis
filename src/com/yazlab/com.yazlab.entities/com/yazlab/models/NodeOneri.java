package com.yazlab.models;

import java.util.ArrayList;

public class NodeOneri {
	String userID;
	ArrayList<Integer> d = new ArrayList<Integer>(); 
	double cosDegeri;
	
	public NodeOneri() {
		super();
	}

	public NodeOneri(String userID, ArrayList<Integer> d, double cosDegeri) {
		super();
		this.userID = userID;
		this.d = d;
		this.cosDegeri = cosDegeri;
	}

	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}

	public ArrayList<Integer> getD() {
		return d;
	}

	public void setD(ArrayList<Integer> d) {
		this.d = d;
	}

	public double getCosDegeri() {
		return cosDegeri;
	}

	public void setCosDegeri(double cosDegeri) {
		this.cosDegeri = cosDegeri;
	}
	
}
