package com.shop.model;

public class Order {
    public int id;
    public int userId;
    public double totalAmount;
    public String orderStatus; // "pending", "processing", "shipped", "delivered", "cancelled"
    
	public int getId() {
		return id;
	}
	public int getUserId() {
		return userId;
	}
	public double getTotalAmount() {
		return totalAmount;
	}
	public String getOrderStatus() {
		return orderStatus;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}
	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}
}