package com.ig.sicurezza.models;

public class PurchaseItem {
	private Long id;
	private String name;
	private Integer amount;
	private Float price;
	private Float priceDollar;
	
	public PurchaseItem(Long id, String name, Integer amount, Float price, Float priceDollar){
		this.id = id;
		this.name = name;
		this.amount = amount;
		this.price = price;
		this.priceDollar = priceDollar;
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
	public Integer getAmount() {
		return amount;
	}
	public void setAmount(Integer amount) {
		this.amount = amount;
	}
	public Float getPrice() {
		return price;
	}
	public void setPrice(Float price) {
		this.price = price;
	}
	public Float getPriceDollar() {
		return priceDollar;
	}
	public void setPriceDollar(Float priceDollar) {
		this.priceDollar = priceDollar;
	}
}
