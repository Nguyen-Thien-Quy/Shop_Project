package com.r2s.demo.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.r2s.demo.model.Address;
import com.r2s.demo.model.User;
import com.r2s.demo.repository.AddressRepository;
import com.r2s.demo.repository.UserRepository;

@Service
public class AddressService {
	@Autowired
	private  AddressRepository addressRepository;
	@Autowired
	private UserRepository userRepository;
	
	public Address addAddress(long userId, Map<String, Object> address) {
		Address newAddress = new Address();
		newAddress.setAddress(address.get("address").toString());
		
		User user = this.userRepository.findById(userId).orElse(null);
		if(!ObjectUtils.isEmpty(user)) {
			newAddress.setUser(user);
		}
		return this.addressRepository.save(newAddress);
	}
	
	public Address updateAddress(long addressId, Map<String, Object> address) {
		Address newAddress = this.addressRepository.findById(addressId).orElse(null);
		newAddress.setAddress(address.get("address").toString());
		return this.addressRepository.save(newAddress);
	}
	
	public List<Address> getAllAddress(){
		return this.addressRepository.findAll();
	}
	
	public void deleteAddress(long id) {
		this.addressRepository.deleteById(id);
	}
	
	public Address findAddress(long id) {
		return this.addressRepository.findById(id).orElse(null);
	}
}
