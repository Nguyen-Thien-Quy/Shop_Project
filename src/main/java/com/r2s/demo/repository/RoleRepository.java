package com.r2s.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.r2s.demo.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long>{
	public Role findRoleByName(String name);
}
