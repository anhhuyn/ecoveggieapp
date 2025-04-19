package vn.iotstar.ecoveggieapp.controllers;

import java.io.File;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import vn.iotstar.ecoveggieapp.config.NetworkUtils;
import vn.iotstar.ecoveggieapp.services.UserService;

@RestController
@RequestMapping("/api/v1")
public class UserController {
	 @Autowired
	 private UserService userService;
	
	@GetMapping("/test")
	public String testEndpoint()
	{
		return "Test endpoint is working";
	}
	
	 // Cập nhật thông tin người dùng
	@PutMapping("/user/update")
    public ResponseEntity<String> updateUser(
            @RequestParam("user_id") int userId,
            @RequestParam("username") String username,
            @RequestParam("gender") String gender,
            @RequestParam("birthday") String birthday,
            @RequestParam(value = "avatar", required = false) MultipartFile avatar
    ) {
        // Xử lý avatar nếu có
        String avatarUrl = null;
        if (avatar != null) {
            // Lưu avatar lên server và lấy URL
            avatarUrl = saveAvatar(avatar);
        }

        int result = userService.updateUserInfo(userId, username, gender, birthday, avatarUrl);

        if (result != 1) {
            return new ResponseEntity<>("fail", HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>("success", HttpStatus.OK);
    }

    // Phương thức lưu avatar và trả về URL
	private String saveAvatar(MultipartFile avatar) {
	    String uploadDir = System.getProperty("user.dir") + "/uploads/";
	    File uploadFolder = new File(uploadDir);
	    if (!uploadFolder.exists()) {
	        uploadFolder.mkdirs();
	    }

	    String fileName = avatar.getOriginalFilename();
	    String filePath = uploadDir + fileName;

	    try {
	        avatar.transferTo(new File(filePath));
	    } catch (IOException e) {
	        e.printStackTrace();
	        return null;
	    }

	    return fileName;
	}



}
