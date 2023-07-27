package com.r2s.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.r2s.demo.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>{

}
