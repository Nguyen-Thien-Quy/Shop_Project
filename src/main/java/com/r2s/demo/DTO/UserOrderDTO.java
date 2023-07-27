package com.r2s.demo.DTO;

import java.util.Date;

import com.r2s.demo.model.Order;

public class UserOrderDTO {
	private Long orderId;
	private Long userId;
	private String name;
	private String email;
	private double totalPrice;
	private String address;
	private Date deliveryTime;
	public UserOrderDTO(Order order) {
		this.orderId = order.getId();
		this.userId = order.getCart().getUser().getId();
		this.name = order.getCart().getUser().getDisplayName();
		this.email = order.getCart().getUser().getEmail();
		this.totalPrice = order.getTotalPrice();
		this.address = order.getAddress();
		this.deliveryTime = order.getDeliveryTime();
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public double getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Date getDeliveryTime() {
		return deliveryTime;
	}
	public void setDeliveryTime(Date deliveryTime) {
		this.deliveryTime = deliveryTime;
	}
	public Long getOrderId() {
		return orderId;
	}
	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	
	
}
