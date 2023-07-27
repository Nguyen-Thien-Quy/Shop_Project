package com.r2s.demo.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.r2s.demo.model.Category;
import com.r2s.demo.model.Product;
import com.r2s.demo.repository.ProductRepository;
import com.r2s.demo.repository.categoryRepository;

@Service
public class ProductService {
	@Autowired
	private ProductRepository productRepository;
	@Autowired
	private categoryRepository categoryRepository;
	
	public Product addProduct(long categoryId, Map<String, Object> product) {
		Product newProduct = new Product();
		Category category = this.categoryRepository.findById(categoryId).orElse(null);
		
		newProduct.setName(product.get("name").toString());
		if(!ObjectUtils.isEmpty(category)) {
			newProduct.setCategory(category);
		}
		
		return this.productRepository.save(newProduct);
	}
	
	public Product updateProduct(long productId, Map<String, Object> product) {
		Product newProduct = this.findProductById(productId);		
		newProduct.setName(product.get("name").toString());
		return this.productRepository.save(newProduct);
	}
	
	public List<Product> getAllProduct(){
		return this.productRepository.findAll();
	}
	
	public void deleteProduct(long id) {
		this.productRepository.deleteById(id);
	}
	
	public Product findProductById(long id) {
		return this.productRepository.findById(id).orElse(null);
	}
}
