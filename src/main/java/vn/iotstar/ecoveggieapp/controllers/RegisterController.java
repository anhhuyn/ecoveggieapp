package vn.iotstar.ecoveggieapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.web.bind.annotation.*;

import vn.iotstar.ecoveggieapp.services.UserService;

@RestController
@RequestMapping("/api/v1")
public class RegisterController {

    @Autowired
    private UserService userService;

    @PostMapping("/user/register")
    public ResponseEntity<?> registerNewUser(
            @RequestParam("username") String username,
            @RequestParam("email") String email,
            @RequestParam("phone") String phone,
            @RequestParam("password") String password) {

        if (username.isEmpty() || email.isEmpty() || phone.isEmpty() || password.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Vui lòng điền đầy đủ thông tin!");
        }

        String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());

        int userId = userService.registerNewUserServiceMethod(username, email, phone, hashedPassword);

        if (userId <= 0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Đăng ký thất bại!");
        }

        return ResponseEntity.status(HttpStatus.OK).body(userId);
    }
}