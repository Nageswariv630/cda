package org.jsp.cda.daoImpl;

import java.util.List;
import java.util.Optional;
import org.jsp.cda.dao.UserDao;
import org.jsp.cda.entity.User;
import org.jsp.cda.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
@Repository
public  class UserDaoImpl implements UserDao{
	@Autowired
	UserRepository repository;
	@Override
	public User saveUser(User user) {
		return repository.save(user);
	}
	@Override
	public Optional<User> findUserById(int id) {
		return repository.findById(id);
	}
	@Override
	public List<User> findAllUsers() {
		return repository.findAll();
	}
	@Override
	public Optional<User> findByUsernameAndPassword(String username,String password) {	
		return   repository.findByUsernameAndPassword(username,password);	
	}
	@Override
	public void deleteUserById(int id) {
		 repository.deleteById(id);
		
	}
}