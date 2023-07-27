package com.r2s.demo.service;

import java.util.Date;
import java.util.List;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.r2s.demo.model.Cart;
import com.r2s.demo.model.CartlineItem;
import com.r2s.demo.model.Order;
import com.r2s.demo.model.User;
import com.r2s.demo.repository.CartLineItemRespository;
import com.r2s.demo.repository.CartRepository;
import com.r2s.demo.repository.OrderRepository;
import com.r2s.demo.repository.UserRepository;

@Service
public class UserServices {
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private OrderRepository orderRepository;
	@Autowired
	private CartLineItemRespository cartLineItemRespository;
	@Autowired
	private CartRepository cartRepository;
//	@Autowired
//	private PasswordEncoder passwordEncoder;
	
	public User addUser(Map<String , Object> user) {
		User newUser = new User();
		
		newUser.setDisplayName(user.get("displayName").toString());
		newUser.setEmail(user.get("email").toString());
		newUser.setUserName(user.get("userName").toString());
		newUser.setPassword(user.get("password").toString());
		
		return this.userRepository.save(newUser);
	}
	
	public User updateUser(long id, Map<String , Object> user) {
		User newUser = this.userRepository.findById(id).orElse(null);
		
		newUser.setDisplayName(user.get("displayName").toString());
		newUser.setEmail(user.get("email").toString());
		newUser.setUserName(user.get("userName").toString());
		newUser.setPassword(user.get("password").toString());
		
		return this.userRepository.save(newUser);
	}
	
	public Order makeOrder(long id, Map<String, Object> order) {
		Order newOrder = new Order();
		User user = this.userRepository.findById(id).orElse(null);
		Cart cart = user.getCart();
		List<CartlineItem> cartlineItems = cart.getCartlineItems();
		for (CartlineItem cartlineItem : cartlineItems) {
			cartlineItem.setDeleted(true);
		}
		newOrder.setCart(cart);
		newOrder.setTotalPrice(cart.getTotalPrice());
		cart.setAmoutProduct(0);
		cart.setTotalPrice(0);
		this.cartRepository.save(cart);
		
		//newOrder.setCart(cart);
		newOrder.setDeliveryTime(new Date());
		newOrder.setAddress(order.get("address").toString());
		
		return this.orderRepository.save(newOrder);
		
	}
	
	public void deleteUser(long id) {
		this.userRepository.deleteById(id);
	}
	
	public List<User> getAllUser(){
		return this.userRepository.findAll();
	}
	
	public User findUserById(long id) {
		return this.userRepository.findById(id).orElse(null);
	}
}
