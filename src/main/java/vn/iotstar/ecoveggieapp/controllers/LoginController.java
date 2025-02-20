package vn.iotstar.ecoveggieapp.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import vn.iotstar.ecoveggieapp.models.UserModel;
import vn.iotstar.ecoveggieapp.services.UserService;

@RestController
@RequestMapping("/api/v1")
public class LoginController {
	
	@Autowired
	UserService userService;
	
	@PostMapping("/user/login")
	public ResponseEntity authenticateUser(@RequestBody UserModel user) {
		
		// Get User Email:
		List<String> userEmail = userService.checkUserEmail(user.getEmail());
		
		// Check If Email is Empty:
		if(userEmail.isEmpty() || userEmail == null) {
			return new ResponseEntity("Email không tồn tại!", HttpStatus.NOT_FOUND);
		}
		//
		
		//Get Hashed User Password:
		String hashed_password = userService.checkUserPasswordByEmail(user.getEmail());
		
		//Validate User Password:
		if(!BCrypt.checkpw(user.getPassword(), hashed_password)) {
			return new ResponseEntity("Tên đăng nhập hoặc mật khẩu không đúng!", HttpStatus.BAD_REQUEST);
		}
		
		//Set User Object:
		UserModel userModel = userService.getUserDetailsByEmail(user.getEmail());
		return new ResponseEntity(userModel, HttpStatus.OK);
		
	}

}
