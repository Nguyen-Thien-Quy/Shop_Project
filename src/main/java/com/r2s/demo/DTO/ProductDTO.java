package com.r2s.demo.DTO;

import java.util.List;

import org.springframework.util.ObjectUtils;

import com.r2s.demo.model.Product;
import com.r2s.demo.model.VariantProduct;

public class ProductDTO {
	private String CategoryName;
	private Long categoryId;
	private String name;
	private Long id;
	private List<VariantProduct> variantProducts;
	public ProductDTO(Product product) {
		this.id = product.getId();
		this.name = product.getName();
		if(!ObjectUtils.isEmpty(product.getCategory())) {
			this.CategoryName = product.getCategory().getName();
			this.categoryId = product.getCategory().getId();
		}
		if(!ObjectUtils.isEmpty(product.getVariantProducts())){
			this.variantProducts = product.getVariantProducts();
		}
	}

	public String getCategoryName() {
		return CategoryName;
	}

	public void setCategoryName(String categoryName) {
		CategoryName = categoryName;
	}

	public Long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public List<VariantProduct> getVariantProducts() {
		return variantProducts;
	}

	public void setVariantProducts(List<VariantProduct> variantProducts) {
		this.variantProducts = variantProducts;
	}
}
