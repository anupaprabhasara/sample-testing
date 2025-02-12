package com.shop.model;

public class Cart {
    public int id;
    public int userId;
    public int productId;
    public int quantity;
    
	public int getId() {
		return id;
	}
	public int getUserId() {
		return userId;
	}
	public int getProductId() {
		return productId;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
}