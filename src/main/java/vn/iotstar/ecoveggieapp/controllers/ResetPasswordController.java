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
public class ResetPasswordController {
	
	@Autowired
	UserService userService;
	
	@PostMapping("/user/resetpassword")
	public ResponseEntity<String> resetPassword(@RequestParam("email") String email,
												@RequestParam("newPassword") String newPassword) {
		if (email.isEmpty() || newPassword.isEmpty()) {
			return new ResponseEntity<>("Vui lòng điền đầy đủ thông tin!", HttpStatus.BAD_REQUEST);
		}

		// Băm mật khẩu mới
		String hashedPassword = BCrypt.hashpw(newPassword, BCrypt.gensalt());

		// Cập nhật mật khẩu trong database
		int result = userService.resetUserPassword(email, hashedPassword);

		if (result != 1) {
			return new ResponseEntity<>("Không thể cập nhật mật khẩu, vui lòng thử lại!", HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>("success", HttpStatus.OK);
	}
}
