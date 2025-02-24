package vn.iotstar.ecoveggieapp.services;

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

	public int registerNewUserServiceMethod(String username, String email, String phone, String password) {
		return userRepository.registerNewUser(username, email, phone, password);
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

}
