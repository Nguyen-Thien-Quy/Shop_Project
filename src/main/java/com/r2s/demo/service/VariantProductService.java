package com.r2s.demo.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.r2s.demo.model.Product;
import com.r2s.demo.model.VariantProduct;
import com.r2s.demo.repository.ProductRepository;
import com.r2s.demo.repository.VariantProductRepository;

@Service
public class VariantProductService {
	@Autowired
	private VariantProductRepository variantProductRepository;
	
	@Autowired
	private ProductRepository productRepository;
	
	public VariantProduct addVariantProduct(long productId, Map<String, Object> variantProduct) {
		VariantProduct newVariantProduct = new VariantProduct();
		newVariantProduct.setColor(variantProduct.get("color").toString());
		newVariantProduct.setSize(variantProduct.get("size").toString());
		newVariantProduct.setModel(variantProduct.get("model").toString());
		newVariantProduct.setPrice(Double.parseDouble(variantProduct.get("price").toString()));
		
		Product product = this.productRepository.findById(productId).orElse(null);
		if(!ObjectUtils.isEmpty(product)) {
			newVariantProduct.setProduct(product);
		}
		return this.variantProductRepository.save(newVariantProduct);
	}
	
	public VariantProduct updateVariantProduct(long variantProductId, Map<String, Object> variantProduct) {
		VariantProduct newVariantProduct = this.variantProductRepository.findById(variantProductId).orElse(null);
		newVariantProduct.setColor(variantProduct.get("color").toString());
		newVariantProduct.setSize(variantProduct.get("size").toString());
		newVariantProduct.setModel(variantProduct.get("model").toString());
		newVariantProduct.setPrice(Double.parseDouble(variantProduct.get("price").toString()));
		
		return this.variantProductRepository.save(newVariantProduct);
	}
	
	public VariantProduct findVariantProduct(long id) {
		return this.variantProductRepository.findById(id).orElse(null);
	}
	
	public List<VariantProduct> getAllVariantProduct() {
		return this.variantProductRepository.findAll();
	}
	
	public void deleteVariantProduct(long id) {
		this.variantProductRepository.deleteById(id);
	}
}
