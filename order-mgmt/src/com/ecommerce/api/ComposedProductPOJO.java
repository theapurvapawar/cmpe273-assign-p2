package com.ecommerce.api;

public class ComposedProductPOJO {
	
	private String product_id;
	private String startDate;
	private String endDate;
	private int quantity;
	private String name;
	private String image_url;
	private double price;
	private String description;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getImage_url() {
		return image_url;
	}

	public void setImage_url(String image_url) {
		this.image_url = image_url;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public ComposedProductPOJO(){}
	
	public ComposedProductPOJO(String product_id,
			String startDate,
			String endDate,
			int quantity,
			int price){
		this.product_id = product_id;
		this.startDate = startDate;
		this.endDate = endDate;
		this.quantity = quantity;
		this.price = price;
	}
	
	public String getProduct_id() {
		return product_id;
	}
	public void setProduct_id(String product_id) {
		this.product_id = product_id;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
}
