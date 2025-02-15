package com.shop.model;

public class DeliveryAddress {
	
	public int id;
	public int userId;
	public String addressLine1; 
	public String addressLine2;
	public String city;
	public String state;
	public String postaleCode;
	public String phone;
	
	public int getId() {
		return id;
	}
	public int getUserId() {
		return userId;
	}
	public String getAddressLine1() {
		return addressLine1;
	}
	public String getAddressLine2() {
		return addressLine2;
	}
	public String getCity() {
		return city;
	}
	public String getState() {
		return state;
	}
	public String getPostaleCode() {
		return postaleCode;
	}
	public String getPhone() {
		return phone;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public void setAddressLine1(String addressLine1) {
		this.addressLine1 = addressLine1;
	}
	public void setAddressLine2(String addressLine2) {
		this.addressLine2 = addressLine2;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public void setState(String state) {
		this.state = state;
	}
	public void setPostaleCode(String postaleCode) {
		this.postaleCode = postaleCode;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}

}



