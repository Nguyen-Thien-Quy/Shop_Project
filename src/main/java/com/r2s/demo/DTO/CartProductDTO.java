package com.r2s.demo.DTO;

import java.util.List;

import org.springframework.util.ObjectUtils;

import com.r2s.demo.model.Cart;
import com.r2s.demo.model.CartlineItem;
import com.r2s.demo.model.VariantProduct;

public class CartProductDTO {
	private long cartId;
	private int amoutproduct;
	private double totalPrice;
	public CartProductDTO(Cart cart) {
		this.cartId = cart.getId();
		this.amoutproduct = cart.getAmoutProduct();
		this.totalPrice = cart.getTotalPrice();
	}
	public long getCartId() {
		return cartId;
	}
	public void setCartId(long cartId) {
		this.cartId = cartId;
	}
	public int getAmoutproduct() {
		return amoutproduct;
	}
	public void setAmoutproduct(int amoutproduct) {
		this.amoutproduct = amoutproduct;
	}
	public double getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}
}
