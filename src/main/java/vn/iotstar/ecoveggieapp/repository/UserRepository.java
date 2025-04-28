package vn.iotstar.ecoveggieapp.repository;

import vn.iotstar.ecoveggieapp.models.UserModel;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import jakarta.transaction.Transactional;

@Repository
public interface UserRepository extends CrudRepository<UserModel, Integer>{

	@Query(value = "SELECT email FROM users WHERE email = :email", nativeQuery = true)
	List<String> checkUserEmail(@Param("email") String email);
	
	@Query(value = "SELECT password FROM users WHERE email = :email", nativeQuery = true)
	String checkUserPasswordByEmail(@Param("email") String email);
	
	@Query(value= "SELECT * FROM users WHERE email = :email", nativeQuery = true)
	UserModel GetUserDetailsByEmail(@Param("email") String email);
	
	/*@Transactional
	@Query(value = "INSERT INTO USERS(username, email, phone, password) OUTPUT INSERTED.ID " +
	               "VALUES(:username, :email, :phone, :password)", nativeQuery = true)
	int registerNewUserAndReturnId(@Param("username") String username,
	                               @Param("email") String email,
	                               @Param("phone") String phone,
	                               @Param("password") String password);*/


	

	@Transactional
	@Modifying
	@Query(value = "UPDATE users SET password = :newPassword WHERE email = :email", nativeQuery = true)
	int updateUserPassword(@Param("email") String email, @Param("newPassword") String newPassword);

	// Sửa tên, giới tính, sinh nhật, avatar
	@Transactional
	@Modifying
	@Query(value = "UPDATE users SET username = :username, gender = :gender, birthday = :birthday, avatar = :avatar WHERE user_id = :user_id", nativeQuery = true)
	int updateUserInfo(@Param("user_id") int userId,
	                   @Param("username") String username,
	                   @Param("gender") String gender,
	                   @Param("birthday") String birthday,
	                   @Param("avatar") String avatar);

}