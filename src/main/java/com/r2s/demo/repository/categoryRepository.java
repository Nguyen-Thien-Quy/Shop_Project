package com.r2s.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.r2s.demo.model.Category;

@Repository
public interface categoryRepository extends JpaRepository<Category, Long>{

}
