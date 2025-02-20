package vn.iotstar.ecoveggieapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import vn.iotstar.ecoveggieapp.services.UserService;

@RestController
@RequestMapping("/api/v1")
public class RegisterController {
	
	@Autowired
	UserService userService;
	
	@PostMapping("/user/register")
	public ResponseEntity registerNewUser(@RequestParam("username")String username,
										@RequestParam("email")String email,
										@RequestParam("phone")String phone,
										@RequestParam("password")String password){
		if(username.isEmpty() || email.isEmpty() || phone.isEmpty() || password.isEmpty())
		{
			return new ResponseEntity<>("Vui lòng điền đầy đủ thông tin!", HttpStatus.BAD_REQUEST);
			
		}
		// Encrypt / Hash Password:
		String hashed_password = BCrypt.hashpw(password, BCrypt.gensalt());
		
		// Register New User:
		int result = userService.registerNewUserServiceMethod(username, email, phone, hashed_password);
		 
		if(result != 1)
		{
			return new ResponseEntity<>("fail", HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>("success", HttpStatus.OK);
	}
		
		
		
}
	

