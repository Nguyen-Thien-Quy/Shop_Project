package com.r2s.demo.DTO;

import org.springframework.util.ObjectUtils;

import com.r2s.demo.model.VariantProduct;

public class VariantProductDTO {
	private Long productId;
	private String productName;
	private Long id;
	private String color;
	private String size;
	private String model;
	private double price;
	public VariantProductDTO(VariantProduct variantProduct) {
		this.id = variantProduct.getId();
		this.color = variantProduct.getColor();
		this.size = variantProduct.getSize();
		this.model = variantProduct.getModel();
		this.price = variantProduct.getPrice();
		
		if(!ObjectUtils.isEmpty(variantProduct.getProduct())) {
			this.productId = variantProduct.getProduct().getId();
			this.productName = variantProduct.getProduct().getName();
		}
	}
	public Long getProductId() {
		return productId;
	}
	public void setProductId(Long productId) {
		this.productId = productId;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public String getSize() {
		return size;
	}
	public void setSize(String size) {
		this.size = size;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
}
