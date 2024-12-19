package org.jsp.cda.dao;

import java.util.List;
import java.util.Optional;

import org.jsp.cda.entity.AuthUser;
import org.jsp.cda.entity.User;
import org.springframework.http.ResponseEntity;

public interface UserDao {
	User saveUser(User user);
	Optional<User> findUserById(int id);
	List<User> findAllUsers();
	Optional<User> findByUsernameAndPassword(String username, String password);

	void deleteUserById(int id);

	
}
