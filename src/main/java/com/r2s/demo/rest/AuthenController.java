package com.r2s.demo.rest;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.r2s.demo.DTO.AuthenDTO;
import com.r2s.demo.DTO.AuthenDTOResponses;
import com.r2s.demo.Utils.JwtUtils;
import com.r2s.demo.constant.BaseRestController;
import com.r2s.demo.constant.Constant;
import com.r2s.demo.service.UserServices;

@RestController
@RequestMapping("/authentication")
public class AuthenController extends BaseRestController{
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private UserServices userServices;

	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody AuthenDTO authen) {
		try {
			this.authenticationManager
					.authenticate(new UsernamePasswordAuthenticationToken(authen.getUserName(), authen.getPassword()));

			String token = JwtUtils.generateToken(authen.getUserName());
			AuthenDTOResponses response = new AuthenDTOResponses(token, "Dang nhap thanh cong!");
			return super.success(response);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return error(Constant.LOGIN_FALSE.getCode(), Constant.LOGIN_FALSE.getMessage());
	}
	
	@PostMapping("/createUser")
	public ResponseEntity<?> addUser(@RequestBody Map<String, Object> user) {
		try {
			if(ObjectUtils.isEmpty(user)) {
				return super.error(Constant.OBJECT_IS_NULL.getCode(), Constant.OBJECT_IS_NULL.getMessage());
			}
			return super.success(this.userServices.addUser(user));
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return super.error(Constant.NO_CONTENT.getCode(), Constant.NO_CONTENT.getMessage());
	}
}
