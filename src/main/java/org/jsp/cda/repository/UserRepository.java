package org.jsp.cda.repository;

import java.util.Optional;

import org.jsp.cda.entity.AuthUser;
import org.jsp.cda.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.ResponseEntity;

public interface UserRepository extends JpaRepository<User, Integer>{

//	@Query("SELECT u FROM User u WHERE u.username= :username AND u.password= :password")
	Optional<User> findByUsernameAndPassword(String username,String password);

	Optional<User> findUserByEmail(String email);


	
	
	
}
