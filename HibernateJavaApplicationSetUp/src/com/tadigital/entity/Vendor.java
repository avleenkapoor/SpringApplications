package com.tadigital.entity;

public class Vendor {
	private int id;
	private String name;
	private String email;
	private String password;
	
public Vendor() {
	// TODO Auto-generated constructor stub
}



@Override
public String toString() {
	return "Vendor [id=" + id + ", name=" + name + ", email=" + email + ", password=" + password + ", getClass()="
			+ getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString() + "]";
}



public int getId() {
	return id;
}



public void setId(int id) {
	this.id = id;
}



public String getName() {
	return name;
}



public void setName(String name) {
	this.name = name;
}



public String getEmail() {
	return email;
}



public void setEmail(String email) {
	this.email = email;
}



public String getPassword() {
	return password;
}



public void setPassword(String password) {
	this.password = password;
}





}
