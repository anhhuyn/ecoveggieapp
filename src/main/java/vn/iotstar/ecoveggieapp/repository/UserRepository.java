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
	
	
	@Transactional
	@Modifying
	@Query(value = "INSERT INTO USERS(username, email, phone, password) VALUES(:username, :email, :phone, :password)", nativeQuery = true)
	int registerNewUser(@Param("username") String username, 
						@Param("email") String email,
						@Param("phone") String phone,
						@Param("password") String password);
}
