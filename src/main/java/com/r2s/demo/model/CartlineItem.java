package com.r2s.demo.model;

import java.util.Calendar;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "Cartline_item")
public class CartlineItem {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private Date addedDate;
	private boolean isDeleted ;
	
	@ManyToOne
	@JsonBackReference
	@JoinColumn(name = "cartId")
	private Cart cart;
	
	@ManyToOne
	@JsonBackReference
	@JoinColumn(name = "variantProductId")
	private VariantProduct variantProduct;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}


	public Date getAddedDate() {
		return addedDate;
	}

	public void setAddedDate(Date addedDate) {
		this.addedDate = addedDate;
	}

	public boolean isDeleted() {
		return isDeleted;
	}

	public void setDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	public Cart getCart() {
		return cart;
	}

	public void setCart(Cart cart) {
		this.cart = cart;
	}

	public VariantProduct getVariantProduct() {
		return variantProduct;
	}

	public void setVariantProduct(VariantProduct variantProduct) {
		this.variantProduct = variantProduct;
	}

	public CartlineItem() {
		super();
	}

	public CartlineItem(Long id, Date addedDate, boolean isDeleted, Cart cart, VariantProduct variantProduct) {
		super();
		this.id = id;
		this.addedDate = addedDate;
		this.isDeleted = isDeleted;
		this.cart = cart;
		this.variantProduct = variantProduct;
	}


	
	
	
	
}
