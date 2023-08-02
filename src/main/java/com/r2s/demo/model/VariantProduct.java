package com.r2s.demo.model;

import java.util.List;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "variant_product")
public class VariantProduct {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String color;
	private String size;
	private String model;
	private double price;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JsonBackReference
	@JoinColumn(name = "productId")
	private Product product;
	
	@OneToMany(mappedBy = "variantProduct")
	@JsonManagedReference
	private List<CartlineItem> cartlineItems;

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

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public List<CartlineItem> getCartlineItems() {
		return cartlineItems;
	}

	public void setCartlineItems(List<CartlineItem> cartlineItems) {
		this.cartlineItems = cartlineItems;
	}

	public VariantProduct(Long id, String color, String size, String model, double price, Product product,
			List<CartlineItem> cartlineItems) {
		super();
		this.id = id;
		this.color = color;
		this.size = size;
		this.model = model;
		this.price = price;
		this.product = product;
		this.cartlineItems = cartlineItems;
	}

	public VariantProduct() {
		super();
	}
	
}
