package com.ig.sicurezza.models;

public class Product implements Comparable<Product> {
	private Long id;
	private String name;
	private Float price;
	private Float priceDollars;
	private String subCategory;
	private String category;
	
	public Product(Long id, String name, float price, float priceDollars, String subCategory, String category) {
		this.id = id;
		this.name = name;
		this.price = price;
		this.priceDollars = priceDollars;
		this.subCategory = subCategory;
		this.category = category;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Float getPrice() {
		return price;
	}
	public void setPrice(Float price) {
		this.price = price;
	}
	public String getSubCategory() {
		return subCategory;
	}
	public void setSubCategory(String subcategory) {
		this.subCategory = subcategory;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public Float getPriceDollars() {
		return priceDollars;
	}
	public void setPriceDollars(Float priceDollars) {
		this.priceDollars = priceDollars;
	}
	@Override
	public int compareTo(Product p) {
		return this.getSubCategory().compareTo(p.getSubCategory());
	}	
	
	
}
