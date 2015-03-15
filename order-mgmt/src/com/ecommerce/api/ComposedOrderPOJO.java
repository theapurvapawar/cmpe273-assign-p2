package com.ecommerce.api;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

@XmlRootElement
public class ComposedOrderPOJO {

	@XmlElement(name="orderNum")
	private int orderNum;
	
	@XmlElement(name="productId")
	private String productId;
	
	@XmlElement(name="orderDate")
	private String orderDate;
	
	@XmlElement(name="qtyOrdered")
	private int qtyOrdered;
	
	@XmlElement(name="name")
	private String name;
	
	@XmlElement(name="description")
	private String description;
	
	@XmlElement(name="cancelledStatus")
	private boolean cancelledStatus;
	
	@XmlElement(name="orderStatus")
	private String orderStatus;
	
	@XmlElement(name="imageURL")
	private String imageURL;
	
	public String getImageURL() {
		return imageURL;
	}

	public void setImageURL(String imageURL) {
		this.imageURL = imageURL;
	}

	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderstatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

	@Override
    public String toString(){
        try {
            // takes advantage of toString() implementation to format {"a":"b"}
            return new JSONObject()
            	.put("orderNum", orderNum)
            	.put("productId", productId)
            	.put("orderDate", orderDate)
            	.put("qtyOrdered", qtyOrdered)
            	.put("name", name)
            	.put("description", description)
            	.put("cancelledStatus", cancelledStatus)
            	.put("orderStatus", orderStatus)
            	.put("imageURL", imageURL)
            	.toString();
        } catch (JSONException e) {
            return null;
        }
    }
	
	public ComposedOrderPOJO(){}
	
	public ComposedOrderPOJO(int orderNum, String productId, String orderDate,
			int qtyOrdered, String name, String description, boolean cancelledStatus,
			String orderStatus, String imageURL
			){
		this.orderNum = orderNum;
		this.productId = productId;
		this.orderDate = orderDate;
		this.qtyOrdered = qtyOrdered;
		this.name = name;
		this.description = description;
		this.cancelledStatus = cancelledStatus;
		this.orderStatus = orderStatus;
		this.imageURL = imageURL;
	}
	
	public boolean getCancelledStatus() {
		return cancelledStatus;
	}
	public void setCancelledStatus(boolean cancelledStatus) {
		this.cancelledStatus = cancelledStatus;
	}
	public int getOrderNum() {
		return orderNum;
	}
	public void setOrderNum(int orderNum) {
		this.orderNum = orderNum;
	}
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public String getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}
	public int getQtyOrdered() {
		return qtyOrdered;
	}
	public void setQtyOrdered(int qtyOrdered) {
		this.qtyOrdered = qtyOrdered;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	
	
}
