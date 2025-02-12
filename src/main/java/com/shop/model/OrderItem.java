package com.shop.model;

public class OrderItem {
    public int id;
    public int orderId;
    public int productId;
    public int quantity;
    public double price;
    
	public int getId() {
		return id;
	}
	public int getOrderId() {
		return orderId;
	}
	public int getProductId() {
		return productId;
	}
	public int getQuantity() {
		return quantity;
	}
	public double getPrice() {
		return price;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public void setPrice(double price) {
		this.price = price;
	}
}