package com.r2s.demo.DTO;

import java.util.List;

import org.springframework.util.ObjectUtils;

import com.r2s.demo.model.Address;
import com.r2s.demo.model.User;

public class UserAddressDTO {
	private Long userId;
	private String userName;
	private String email;
	private String displayName;
	private List<Address> addresses;
	public UserAddressDTO(User user) {
		this.userId = user.getId();
		this.userName = user.getUserName();
		this.displayName = user.getDisplayName();
		this.email = user.getEmail();
		if(!ObjectUtils.isEmpty(user.getAddress())) {
			this.addresses = user.getAddress();
		}
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getDisplayName() {
		return displayName;
	}
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}
	public List<Address> getAddresses() {
		return addresses;
	}
	public void setAddresses(List<Address> addresses) {
		this.addresses = addresses;
	}
	
	
}
