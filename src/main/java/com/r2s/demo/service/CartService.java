package com.r2s.demo.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.r2s.demo.model.Cart;
import com.r2s.demo.model.CartlineItem;
import com.r2s.demo.model.Product;
import com.r2s.demo.model.User;
import com.r2s.demo.model.VariantProduct;
import com.r2s.demo.repository.CartLineItemRespository;
import com.r2s.demo.repository.CartRepository;
import com.r2s.demo.repository.ProductRepository;
import com.r2s.demo.repository.UserRepository;
import com.r2s.demo.repository.VariantProductRepository;

@Service
public class CartService {
	@Autowired
	private CartRepository cartRepository;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private CartLineItemRespository cartLineItemRespository;
	@Autowired
	private VariantProductRepository variantProductRepository;

	public Cart addCart(long userId) {
		Cart newCart = new Cart();
		User user = this.userRepository.findById(userId).orElse(null);

		newCart.setCreatedDate(new Date());
		if (!ObjectUtils.isEmpty(user)) {
			newCart.setUser(user);
		}
		newCart.setTotalPrice(0);
		newCart.setAmoutProduct(0);

		return this.cartRepository.save(newCart);
	}

	public Cart updateCart(long cartId, Map<String, Object> cart) {
		Cart newCart = this.cartRepository.findById(cartId).orElse(null);

		try {
			newCart.setCreatedDate(new SimpleDateFormat("dd/MM/yyyy").parse(cart.get("createdDate").toString()));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return this.cartRepository.save(newCart);
	}
	
	public List<CartlineItem> findAllByDeleted(boolean isDeleted, List<CartlineItem> cartlineItems){
		List<CartlineItem> isTrue = new ArrayList<CartlineItem>();
		List<CartlineItem> isfalse = new ArrayList<CartlineItem>();
		
		for (CartlineItem cartlineItem : cartlineItems) {
			if(cartlineItem.isDeleted() == true) {
				isTrue.add(cartlineItem);
			}
			if(cartlineItem.isDeleted() == false) {
				isfalse.add(cartlineItem);
			}
		}
		
		if(isDeleted) {
			return isTrue;
		}else {
			return isfalse;
		}

	}

	public Cart addProductOnCart(long cartId, long variantProductId) {
		VariantProduct variantProduct = this.variantProductRepository.findById(variantProductId).orElse(null);
		Cart cart = this.cartRepository.findById(cartId).orElse(null);
		CartlineItem cartlineItem = new CartlineItem();
		
		cartlineItem.setAddedDate(new Date());
		cartlineItem.setDeleted(false);
		cartlineItem.setVariantProduct(variantProduct);
		cart.getCartlineItems().add(cartlineItem);
		cartlineItem.setCart(cart);
//		this.cartRepository.save(cart);

//		Cart newCart = this.findCartById(cartId);
		List<CartlineItem> cartlineItems = this.findAllByDeleted(false, cart.getCartlineItems());
		int amout_product = 0;
		double total_price = 0;
		for (CartlineItem cartlineItem2 : cartlineItems) {
			total_price+= cartlineItem2.getVariantProduct().getPrice();
			amout_product++;
		}

		cart.setAmoutProduct(amout_product);
		cart.setTotalPrice(total_price);
		return this.cartRepository.save(cart);
	}

	public List<Cart> getAllCart() {
		return this.cartRepository.findAll();
	}

	public void deleteCart(long id) {
		this.cartRepository.deleteById(id);
	}

	public Cart findCartById(long id) {
		return this.cartRepository.findById(id).orElse(null);
	}
}
