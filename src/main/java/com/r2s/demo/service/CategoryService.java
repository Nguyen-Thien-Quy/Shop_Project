package com.r2s.demo.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.r2s.demo.model.Category;
import com.r2s.demo.repository.categoryRepository;

@Service
public class CategoryService {
	@Autowired
	private categoryRepository categoryRepository;
	
	public Category addCategory(Map<String, Object> category) {
		Category newCategory = new Category();
		newCategory.setName(category.get("name").toString());
		newCategory.setDescription(category.get("description").toString());
		
		return this.categoryRepository.save(newCategory);
	}
	
	public Category updateCategory(long id, Map<String, Object> category) {
		Category newCategory = this.findById(id);
		
		newCategory.setName(category.get("name").toString());
		newCategory.setDescription(category.get("description").toString());
		
		return this.categoryRepository.save(newCategory);
	}
	
	public List<Category> getAllCategory(){
		return this.categoryRepository.findAll();
	}
	
	public void deleteCategory(long id) {
		this.categoryRepository.deleteById(id);
	}
	public Category findById(long id) {
		return this.categoryRepository.findById(id).orElse(null);
	}
}
