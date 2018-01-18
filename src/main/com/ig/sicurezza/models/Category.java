package com.ig.sicurezza.models;

import java.util.ArrayList;
import java.util.List;

public class Category {
	private String name;
	private List<SubCategory> subcategories;

	public Category(String name) {
		this.subcategories = new ArrayList<SubCategory>();		
		this.name = name;
	}

	public List<SubCategory> getSubcategories() {
		return subcategories;
	}

	public void setSubcategories(List<SubCategory> subcategories) {
		this.subcategories = subcategories;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void addSubCategory(SubCategory newSub) {
		this.subcategories.add(newSub);		
	}

}
