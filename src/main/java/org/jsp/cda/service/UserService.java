package org.jsp.cda.service;

import org.jsp.cda.entity.AuthUser;
import org.jsp.cda.entity.User;
import org.springframework.http.ResponseEntity;

public interface UserService {
	ResponseEntity<?> saveUser(User user);

	ResponseEntity<?> findUserById(int id);

	ResponseEntity<?> findAllUsers();
	
	ResponseEntity<?> findByUsernameAndPassword(AuthUser authUser);

	String deletUserById(int id);

	ResponseEntity<?> verifyOTP(int id, int otp);

	ResponseEntity<?> findUserByEmail(String email);	

}
