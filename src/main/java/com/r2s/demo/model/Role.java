package com.r2s.demo.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "role")
public class Role {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String name;
	private String description;

	@OneToMany(mappedBy = "role", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JsonManagedReference
	List<RoleUser> roleUsers;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<RoleUser> getRoleUsers() {
		return roleUsers;
	}

	public void setRoleUsers(List<RoleUser> roleUsers) {
		this.roleUsers = roleUsers;
	}

	public Role(Long id, String name, String description, List<RoleUser> roleUsers) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.roleUsers = roleUsers;
	}

	public Role() {
		super();
	}
	
	
}
