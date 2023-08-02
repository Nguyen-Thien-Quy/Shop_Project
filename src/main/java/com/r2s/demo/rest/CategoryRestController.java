package com.r2s.demo.rest;

import java.awt.print.Pageable;

import java.util.List;
import java.util.Map;

import javax.swing.text.AbstractDocument.Content;

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

import com.r2s.demo.constant.BaseRestController;
import com.r2s.demo.constant.Constant;
import com.r2s.demo.model.Category;
import com.r2s.demo.service.CategoryService;

@RestController
@RequestMapping("/categories")
public class CategoryRestController extends BaseRestController{
	@Autowired
	private CategoryService categoryService;
	
	@GetMapping
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<?> getAllCategory(){
		try {
			List<Category> categories = this.categoryService.getAllCategory();
			
			if(ObjectUtils.isEmpty(categories)) {
				return super.error(Constant.OBJECT_IS_NULL.getCode(), Constant.OBJECT_IS_NULL.getMessage());
			}
			
			return super.success(categories);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return super.error(Constant.NO_CONTENT.getCode(), Constant.NO_CONTENT.getMessage());
	}
	
	@PostMapping
	@PreAuthorize("hasAnyRole('USER')")
	public ResponseEntity<?> addCategory(@RequestBody Map<String, Object> category){
		try {
			if(ObjectUtils.isEmpty(category)) {
				return super.error(Constant.OBJECT_IS_NULL.getCode(), Constant.OBJECT_IS_NULL.getMessage());
			}
			
			return super.success(this.categoryService.addCategory(category));
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return super.error(Constant.NO_CONTENT.getCode(), Constant.NO_CONTENT.getMessage());
	}
	
	@PutMapping
	@PreAuthorize("hasAnyRole('USER')")
	public ResponseEntity<?> updateCategory(@RequestParam(name = "id") long id, @RequestBody Map<String, Object> category){
		try {
			if(ObjectUtils.isEmpty(category) || ObjectUtils.isEmpty(this.categoryService.findById(id))) {
				return super.error(Constant.OBJECT_IS_NULL.getCode(), Constant.OBJECT_IS_NULL.getMessage());
			}
			
			return super.success(this.categoryService.updateCategory(id, category));
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return super.error(Constant.NO_CONTENT.getCode(), Constant.NO_CONTENT.getMessage());
	}
	
	@DeleteMapping
	@PreAuthorize("hasAnyRole('USER')")
	public ResponseEntity<?> deleteCategory(@RequestParam(name = "id") long id){
		try {
			Category category= this.categoryService.findById(id);
			if(ObjectUtils.isEmpty(category)) {
				return super.error(Constant.OBJECT_IS_NULL.getCode(), Constant.OBJECT_IS_NULL.getMessage());
			}
			this.categoryService.deleteCategory(id);
			return success(category);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return super.error(Constant.NO_CONTENT.getCode(), Constant.NO_CONTENT.getMessage());
	}
}
