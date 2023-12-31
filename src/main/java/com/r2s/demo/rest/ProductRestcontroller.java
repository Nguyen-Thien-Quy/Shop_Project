package com.r2s.demo.rest;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.r2s.demo.DTO.ProductDTO;
import com.r2s.demo.constant.BaseRestController;
import com.r2s.demo.constant.Constant;
import com.r2s.demo.model.Category;
import com.r2s.demo.model.Product;
import com.r2s.demo.service.ProductService;

@RestController
@RequestMapping("/products")
public class ProductRestcontroller extends BaseRestController{
	@Autowired
	private ProductService productService;
	
	@GetMapping
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<?> getAllProduct(){
		try {
			List<Product> products = this.productService.getAllProduct();
			List<ProductDTO> responses = products.stream().map(ProductDTO::new).toList();
			
			if(ObjectUtils.isEmpty(responses)) {
				return super.error(Constant.OBJECT_IS_NULL.getCode(), Constant.OBJECT_IS_NULL.getMessage());
			}
			return success(responses);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return super.error(Constant.NO_CONTENT.getCode(), Constant.NO_CONTENT.getMessage());
	}
	
	@PostMapping
	@PreAuthorize("hasAnyRole('USER')")
	public ResponseEntity<?> AddProduct(@RequestParam(name = "categoryId") long id, @RequestBody Map<String, Object> product){
		try {	
			if(ObjectUtils.isEmpty(product)) {
				return super.error(Constant.OBJECT_IS_NULL.getCode(), Constant.OBJECT_IS_NULL.getMessage());
			}
			return super.success(this.productService.addProduct(id, product));
		} catch (Exception e) {
			// TODO: handle exception
		}
		return super.error(Constant.NO_CONTENT.getCode(), Constant.NO_CONTENT.getMessage());
	}
	
	@PutMapping
	@PreAuthorize("hasAnyRole('USER')")
	public ResponseEntity<?> updateProduct(@RequestParam(name = "productId") long id, @RequestBody Map<String, Object> product){
		try {	
			if(ObjectUtils.isEmpty(product) || ObjectUtils.isEmpty(this.productService.findProductById(id))) {
				return super.error(Constant.OBJECT_IS_NULL.getCode(), Constant.OBJECT_IS_NULL.getMessage());
			}
			return super.success(this.productService.updateProduct(id, product));
		} catch (Exception e) {
			// TODO: handle exception
		}
		return super.error(Constant.NO_CONTENT.getCode(), Constant.NO_CONTENT.getMessage());
	}
	
	@DeleteMapping
	@PreAuthorize("hasAnyRole('USER')")
	public ResponseEntity<?> deleteProduct(@RequestParam(name = "productId") long id){
		try {
			Product product= this.productService.findProductById(id);
			if(ObjectUtils.isEmpty(product)) {
				return super.error(Constant.OBJECT_IS_NULL.getCode(), Constant.OBJECT_IS_NULL.getMessage());
			}
			this.productService.deleteProduct(id);
			return success(product);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return super.error(Constant.NO_CONTENT.getCode(), Constant.NO_CONTENT.getMessage());
	}
}
