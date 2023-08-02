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
import com.r2s.demo.model.User;
import com.r2s.demo.repository.CartRepository;
import com.r2s.demo.service.AddressService;

@RestController
@RequestMapping("/addresses")
public class AddressRestController extends BaseRestController{
	@Autowired
	private AddressService addressService;
	@Autowired
	private CartRepository cartRepository;
	
	@GetMapping
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<?> getAllAddress() {
		try {
			List<Address> addresses = this.addressService.getAllAddress();
			if(ObjectUtils.isEmpty(addresses)) {
				return super.error(Constant.OBJECT_IS_NULL.getCode(), Constant.OBJECT_IS_NULL.getMessage());
			}
			return super.success(addresses);
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return super.error(Constant.NO_CONTENT.getCode(), Constant.NO_CONTENT.getMessage());
	}
	
	@PreAuthorize("hasAnyRole('USER')")
	@PostMapping
	public ResponseEntity<?> addAddress(@RequestParam(name = "userId") long id, @RequestBody Map<String, Object> address) {
		try {
			
			if(ObjectUtils.isEmpty(address)) {
				return super.error(Constant.OBJECT_IS_NULL.getCode(), Constant.OBJECT_IS_NULL.getMessage());
			}
			return super.success(this.addressService.addAddress(id, address));
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return super.error(Constant.NO_CONTENT.getCode(), Constant.NO_CONTENT.getMessage());
	}
	
	@PreAuthorize("hasAnyRole('USER')")
	@PutMapping
	public ResponseEntity<?> updateAddress(@RequestParam(name = "addressId") long id, @RequestBody Map<String, Object> address) {
		try {
			if(ObjectUtils.isEmpty(address)) {
				return super.error(Constant.OBJECT_IS_NULL.getCode(), Constant.OBJECT_IS_NULL.getMessage());
			}
			if(ObjectUtils.isEmpty(this.addressService.findAddress(id))) {
				return super.error(Constant.NOT_FOUND.getCode(), Constant.NOT_FOUND.getMessage());
			}
			return super.success(this.addressService.updateAddress(id, address));	
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return super.error(Constant.NO_CONTENT.getCode(), Constant.NO_CONTENT.getMessage());
	}
	
	@PreAuthorize("hasAnyRole('USER')")
	@DeleteMapping
	public ResponseEntity<?> deleteAddress(@RequestParam(name = "addressId") long id){
		try {
			Address address = this.addressService.findAddress(id);
			if(!ObjectUtils.isEmpty(address)) {
				this.addressService.deleteAddress(id);
				return success(address);
			}
			return super.error(Constant.OBJECT_IS_NULL.getCode(), Constant.OBJECT_IS_NULL.getMessage());
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return super.error(Constant.NO_CONTENT.getCode(), Constant.NO_CONTENT.getMessage());
	}
}
