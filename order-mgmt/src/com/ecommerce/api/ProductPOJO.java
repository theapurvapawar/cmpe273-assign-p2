package com.ecommerce.api;


import com.fasterxml.jackson.annotation.JsonAnySetter;

public class ProductPOJO {

	private String product_id;
	private String startDate;
	private String endDate;
	private String name;
	private String image_url;
	private double price;
	private String description;
	
	public ProductPOJO(){}
	
	public ProductPOJO(String product_id, String startDate, String endDate){
		this.product_id = product_id;
		this.startDate = startDate;
		this.endDate = endDate;
	}
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
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
	
	@JsonAnySetter
	  public void handleUnknown(String key, Object value) {
	    // ignore; handles fields which are not required
	}
	
	

}