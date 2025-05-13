package vn.iotstar.ecoveggieapp.services;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import vn.iotstar.ecoveggieapp.models.UserModel;
import vn.iotstar.ecoveggieapp.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	CartService cartService;
	
	 @Transactional
	    public int registerNewUserServiceMethod(String username, String email, String phone, String password) {
	        UserModel user = new UserModel();
	        user.setUsername(username);
	        user.setEmail(email);
	        user.setPhone(phone);
	        user.setPassword(password);
	        user.setCreated_at(new Date()); // Set ngày tạo lúc đăng ký

	        UserModel savedUser = userRepository.save(user); // save() => tự sinh ID
	        cartService.createCartForUser(savedUser);
	        return savedUser.getUser_id();
	    }


	// End Of Register New User Service Method.

	public List<String> checkUserEmail(String email) {
		return userRepository.checkUserEmail(email);
	}
	// End of Check User Email Service Method.

	public String checkUserPasswordByEmail(String password) {
		return userRepository.checkUserPasswordByEmail(password);
	}
	// End of Check User Password Service Method.

	public UserModel getUserDetailsByEmail(String email) {
		return userRepository.GetUserDetailsByEmail(email);
	}
	// End of Get User Detail By Email.

	public int resetUserPassword(String email, String newPassword) {
		return userRepository.updateUserPassword(email, newPassword);
	}
	
	@Transactional
	public int updateUserInfo(int userId, String username, String gender, String birthday, String avatar) {
	    // Lưu thông tin người dùng và avatar vào cơ sở dữ liệu
	    return userRepository.updateUserInfo(userId, username, gender, birthday, avatar);
	}

	// End of Sửa tên, giới tính, sinh nhật, avatar

	
	
	

}