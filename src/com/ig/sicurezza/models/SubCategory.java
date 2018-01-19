package com.ig.sicurezza.models;

import java.util.ArrayList;
import java.util.List;

public class SubCategory {
	private String name;
	private List<Product> products;
	
	public SubCategory(String name){
		this.name = name;
		this.products = new ArrayList<Product>();
	}

	public List<Product> getProducts() {
		return products;
	}
	
	public void addProduct(Product product){
		this.products.add(product);
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
