package com.ig.sicurezza.models;

public class Customer {
	private String company;
	private String name;
	private String email;
	
	public Customer(String company, String name, String email) {
		this.company = company;
		this.name = name;
		this.email = email;
	}
	
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
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
}
