package com.r2s.demo.model;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "cart")
public class Cart {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private Date createdDate;
	
	private int amoutProduct = 0;
	private double totalPrice = 0;
	
	@OneToOne(cascade = CascadeType.MERGE)
	@JsonBackReference
	@JoinColumn(name = "userId", referencedColumnName = "id")
	private User user;
	
	@OneToMany(mappedBy = "cart",cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JsonManagedReference
	private List<CartlineItem> cartlineItems;

	@OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JsonManagedReference
	private List<Order> orders;
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<CartlineItem> getCartlineItems() {
		return cartlineItems;
	}

	public void setCartlineItems(List<CartlineItem> cartlineItems) {
		this.cartlineItems = cartlineItems;
	}


	public List<Order> getOrders() {
		return orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}

	public Cart() {
		super();
	}

	public int getAmoutProduct() {
		return amoutProduct;
	}

	public void setAmoutProduct(int amoutProduct) {
		this.amoutProduct = amoutProduct;
	}

	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public Cart(Long id, Date createdDate, int amoutProduct, double totalPrice, User user,
			List<CartlineItem> cartlineItems, List<Order> orders) {
		super();
		this.id = id;
		this.createdDate = createdDate;
		this.amoutProduct = amoutProduct;
		this.totalPrice = totalPrice;
		this.user = user;
		this.cartlineItems = cartlineItems;
		this.orders = orders;
	}

	
	
	
}
