package com.r2s.demo.rest;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.r2s.demo.constant.BaseRestController;
import com.r2s.demo.constant.Constant;
import com.r2s.demo.model.Address;
import com.r2s.demo.model.Cart;
import com.r2s.demo.service.CartService;
import com.r2s.demo.service.UserServices;

@RestController
@RequestMapping("/carts")
public class CartRestController extends BaseRestController{
	@Autowired
	private CartService cartService;
	@Autowired
	private UserServices userServices;
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@GetMapping
	public ResponseEntity<?> getAllCart() {
		try {
			List<Cart> carts = this.cartService.getAllCart();
			if(ObjectUtils.isEmpty(carts)) {
				return super.error(Constant.OBJECT_IS_NULL.getCode(), Constant.OBJECT_IS_NULL.getMessage());
			}
			return super.success(carts);
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return super.error(Constant.NO_CONTENT.getCode(), Constant.NO_CONTENT.getMessage());
	}
	
	@GetMapping("/findCartLineItem")
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<?> getAllCartLineItem(@RequestParam(name = "cartId") long cartId, @RequestParam(name = "status") int status) {
		try {
			if(status == 0) {
				Cart cart = this.cartService.findCartById(cartId);
				if(ObjectUtils.isEmpty(cart)) {
					return super.error(Constant.OBJECT_IS_NULL.getCode(), Constant.OBJECT_IS_NULL.getMessage());
				}
				return super.success(this.cartService.findAllByDeleted(false, cart.getCartlineItems()));
			} else
			if(status == 1) {
				Cart cart = this.cartService.findCartById(cartId);
				if(ObjectUtils.isEmpty(cart)) {
					return super.error(Constant.OBJECT_IS_NULL.getCode(), Constant.OBJECT_IS_NULL.getMessage());
				}
				return super.success(this.cartService.findAllByDeleted(true, cart.getCartlineItems()));
			}else {
				Cart cart = this.cartService.findCartById(cartId);
				if(ObjectUtils.isEmpty(cart)) {
					return super.error(Constant.OBJECT_IS_NULL.getCode(), Constant.OBJECT_IS_NULL.getMessage());
				}
				return this.success(cart.getCartlineItems());
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return super.error(Constant.NO_CONTENT.getCode(), Constant.NO_CONTENT.getMessage());
	}
	
	@PostMapping
	@PreAuthorize("hasAnyRole('ADMIN', 'USER')")
	public ResponseEntity<?> addCart(@RequestParam(name = "userId") long id) {
		try {
			
			if(ObjectUtils.isEmpty(this.userServices.findUserById(id))) {
				return super.error(Constant.NOT_FOUND.getCode(), Constant.NOT_FOUND.getMessage());
			}
			return super.success(this.cartService.addCart(id));
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return super.error(Constant.NO_CONTENT.getCode(), Constant.NO_CONTENT.getMessage());
	}
	
	@PostMapping("/addProduct")
	@PreAuthorize("hasAnyRole('ADMIN', 'USER')")
	public ResponseEntity<?> addProductOnCart(@RequestParam(name = "cartId") long cartId, @RequestParam(name = "variantProductId") long variantProductId) {
		try {
			if(ObjectUtils.isEmpty(this.cartService.findCartById(cartId))) {
				return super.error(Constant.NOT_FOUND.getCode(), Constant.NOT_FOUND.getMessage());
			}
			
			Cart cart = this.cartService.addProductOnCart(cartId, variantProductId);
			return super.success(cart);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return super.error(Constant.NO_CONTENT.getCode(), Constant.NO_CONTENT.getMessage());
	}
	
	@PutMapping
	@PreAuthorize("hasAnyRole('ADMIN', 'USER')")
	public ResponseEntity<?> updateCart(@RequestParam(name = "cartId") long id, @RequestBody Map<String, Object> cart) {
		try {
			if(ObjectUtils.isEmpty(cart)) {
				return super.error(Constant.OBJECT_IS_NULL.getCode(), Constant.OBJECT_IS_NULL.getMessage());
			}
			if(ObjectUtils.isEmpty(this.cartService.findCartById(id))) {
				return super.error(Constant.NOT_FOUND.getCode(), Constant.NOT_FOUND.getMessage());
			}
			return super.success(this.cartService.updateCart(id, cart));	
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return super.error(Constant.NO_CONTENT.getCode(), Constant.NO_CONTENT.getMessage());
	}
	
	@DeleteMapping
	@PreAuthorize("hasAnyRole('ADMIN', 'USER')")
	public ResponseEntity<?> deleteCart(@RequestParam(name = "cartId") long id){
		try {
			Cart cart = this.cartService.findCartById(id);
			if(!ObjectUtils.isEmpty(cart)) {
				this.cartService.deleteCart(id);
				return super.success(cart);
			}
			return super.error(Constant.OBJECT_IS_NULL.getCode(), Constant.OBJECT_IS_NULL.getMessage());
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return super.error(Constant.NO_CONTENT.getCode(), Constant.NO_CONTENT.getMessage());
	}
}
