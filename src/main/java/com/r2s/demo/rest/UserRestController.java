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

import com.r2s.demo.DTO.UserAddressDTO;
import com.r2s.demo.DTO.UserOrderDTO;
import com.r2s.demo.constant.BaseRestController;
import com.r2s.demo.constant.Constant;
import com.r2s.demo.model.Order;
import com.r2s.demo.model.User;
import com.r2s.demo.service.UserServices;

@RestController
@RequestMapping("/users")
public class UserRestController extends BaseRestController{
	@Autowired
	private UserServices userServices;
	
	@GetMapping
	public ResponseEntity<?> getAllUser() {
		try {
			List<User> users = this.userServices.getAllUser();
			List<UserAddressDTO> responses = users.stream().map(UserAddressDTO::new).toList();
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
	public ResponseEntity<?> addUser(@RequestBody Map<String, Object> user) {
		try {
			if(ObjectUtils.isEmpty(user)) {
				return super.eror(Constant.OBJECT_IS_NULL.getCode(), Constant.OBJECT_IS_NULL.getMassage());
			}
			return super.success(this.userServices.addUser(user));
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return super.eror(Constant.NO_CONTENT.getCode(), Constant.NO_CONTENT.getMassage());
	}
	
	@PostMapping("/makeOrder")
	public ResponseEntity<?> makeOser(@RequestParam(name = "userId") long id,@RequestBody Map<String, Object> order) {
		try {
//			if(ObjectUtils.isEmpty(order)) {
//				return super.eror(Constant.OBJECT_IS_NULL.getCode(), Constant.OBJECT_IS_NULL.getMassage());
//			}
			if(ObjectUtils.isEmpty(this.userServices.findUserById(id))) {
				return super.eror(Constant.NOT_FOUND.getCode(), Constant.NOT_FOUND.getMassage());
			}
			Order newOrder = this.userServices.makeOrder(id, order);
			return super.success(new UserOrderDTO(newOrder));
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return super.eror(Constant.NO_CONTENT.getCode(), Constant.NO_CONTENT.getMassage());
	}
	
	@PutMapping
	public ResponseEntity<?> updateUser(@RequestParam(name = "id") long id, @RequestBody Map<String, Object> user) {
		try {
			if(ObjectUtils.isEmpty(user)) {
				return super.eror(Constant.OBJECT_IS_NULL.getCode(), Constant.OBJECT_IS_NULL.getMassage());
			}
			if(ObjectUtils.isEmpty(this.userServices.findUserById(id))) {
				return super.eror(Constant.NOT_FOUND.getCode(), Constant.NOT_FOUND.getMassage());
			}
			return super.success(this.userServices.updateUser(id, user));	
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return super.eror(Constant.NO_CONTENT.getCode(), Constant.NO_CONTENT.getMassage());
	}
	
	@DeleteMapping
	public ResponseEntity<?> deleteUser(@RequestParam(name = "id") long id){
		try {
			User user = this.userServices.findUserById(id);
			if(!ObjectUtils.isEmpty(user)) {
				this.userServices.deleteUser(id);
				return success(user);
			}
			return super.eror(Constant.OBJECT_IS_NULL.getCode(), Constant.OBJECT_IS_NULL.getMassage());
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return super.eror(Constant.NO_CONTENT.getCode(), Constant.NO_CONTENT.getMassage());
	}
}
