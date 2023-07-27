package com.r2s.demo.rest;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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

import com.r2s.demo.DTO.VariantProductDTO;
import com.r2s.demo.constant.BaseRestController;
import com.r2s.demo.constant.Constant;
import com.r2s.demo.model.VariantProduct;
import com.r2s.demo.repository.VariantProductRepository;
import com.r2s.demo.service.ProductService;
import com.r2s.demo.service.VariantProductService;

@RestController
@RequestMapping("/variantproducts")
public class VariantProductRestcontroller extends BaseRestController{
	@Autowired
	private VariantProductService variantProductService;
	
	@Autowired
	private ProductService productService;
	
	@GetMapping
	public ResponseEntity<?> getAllVariantproduct(){
		try {
			List<VariantProduct> variantProducts = this.variantProductService.getAllVariantProduct();
			List<VariantProductDTO> responses = variantProducts.stream().map(VariantProductDTO::new).toList();
			
			if(ObjectUtils.isEmpty(responses)) {
				return super.eror(Constant.OBJECT_IS_NULL.getCode(), Constant.OBJECT_IS_NULL.getMassage());
			}
			return super.success(responses);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return super.eror(Constant.NO_CONTENT.getCode(), Constant.NO_CONTENT.getMassage());
	}
	
	@PostMapping
	public ResponseEntity<?> addVariantProduct(@RequestParam(name = "productId") long id, @RequestBody Map<String, Object> variantProduct){
		try {
			if(ObjectUtils.isEmpty(this.productService.findProductById(id))) {
				return super.eror(Constant.OBJECT_IS_NULL.getCode(), Constant.OBJECT_IS_NULL.getMassage());
			}
			
			if(ObjectUtils.isEmpty(variantProduct)) {
				return super.eror(Constant.OBJECT_IS_NULL.getCode(), Constant.OBJECT_IS_NULL.getMassage());
			}
			return super.success(this.variantProductService.addVariantProduct(id, variantProduct));
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return super.eror(Constant.NO_CONTENT.getCode(), Constant.NO_CONTENT.getMassage());
	}
	
	@PutMapping
	public ResponseEntity<?> updateVariantproduct(@RequestParam(name = "variantProductId") long id, @RequestBody Map<String, Object> variantProduct){
		try {
			if(ObjectUtils.isEmpty(this.variantProductService.findVariantProduct(id))) {
				return super.eror(Constant.OBJECT_IS_NULL.getCode(), Constant.OBJECT_IS_NULL.getMassage());
			}
			
			if(ObjectUtils.isEmpty(variantProduct)) {
				return super.eror(Constant.OBJECT_IS_NULL.getCode(), Constant.OBJECT_IS_NULL.getMassage());
			}
			return super.success(this.variantProductService.updateVariantProduct(id, variantProduct));
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return super.eror(Constant.NO_CONTENT.getCode(), Constant.NO_CONTENT.getMassage());
	}
	
	
	@DeleteMapping
	public ResponseEntity<?> deleteVariantproduct(@RequestParam(name = "productId") long id){
		try {
			VariantProduct variantProduct = this.variantProductService.findVariantProduct(id);
			if(ObjectUtils.isEmpty(variantProduct)) {
				return super.eror(Constant.OBJECT_IS_NULL.getCode(), Constant.OBJECT_IS_NULL.getMassage());
			}
			this.variantProductService.deleteVariantProduct(id);
			return super.success(variantProduct);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return super.eror(Constant.NO_CONTENT.getCode(), Constant.NO_CONTENT.getMassage());
	}
}
