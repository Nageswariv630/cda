package org.jsp.cda.serviceImpl;

import java.util.Optional;
import org.jsp.cda.dao.UserDao;
import org.jsp.cda.entity.AuthUser;
import org.jsp.cda.entity.User;
import org.jsp.cda.exceptionclasses.UserNotFoundException;
import org.jsp.cda.service.UserService;
import org.jsp.cda.structure.ResponseStructure;
import org.jsp.cda.util.MyUtil;
import org.jsp.cda.util.UserStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
@Service
public class UserServiceImpl implements UserService{
	@Autowired
	private UserDao userDao;
	@Autowired
	private JavaMailSender javaMailSender;
	
	@Override
	public ResponseEntity<?> saveUser(User user) {	
		user.setOtp(MyUtil.getOTP());
		user =userDao.saveUser(user);	
		MimeMessage mimeMessage=javaMailSender.createMimeMessage();
		try {
			MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
			mimeMessageHelper.addTo(user.getEmail());
			mimeMessageHelper.setSubject("Account Created");
			mimeMessageHelper.setText("<html><body style='background:cyan; color:crimson;'><h1>Hello "+user.getName()+" Your CDA Account Has Been Created Successfullly</h1><br><br><hr><br> Your OTP"+user.getOtp()+"</body></html>",true);
			javaMailSender.send(mimeMessage);
			
		} catch (MessagingException e) {
			e.printStackTrace();
		}
		return ResponseEntity.status(HttpStatus.OK).body(ResponseStructure.builder().
				httpStatus(HttpStatus.OK.value()).message("User Login Successfully...").body(user).build());
	}
	
	
	@Override
	public ResponseEntity<?> findUserById(int id) {
		Optional<User>  optional=userDao.findUserById(id);
		if(optional.isEmpty())
			throw UserNotFoundException.builder().message("Invalid User Id: "+id).build();
		User user=optional.get();
		return ResponseEntity.status(HttpStatus.OK).body(ResponseStructure.builder().httpStatus(HttpStatus.OK.value()).message("User FoundBy id..").body(optional).build());
	}
	
	
	@Override
	public ResponseEntity<?> findAllUsers() {
		return ResponseEntity.status(HttpStatus.OK).body(ResponseStructure.builder().httpStatus(HttpStatus.OK.value()).message("All Users Found Successfully...").body(userDao.findAllUsers()).build());
	}
	
	@Override
	public ResponseEntity<?> findByUsernameAndPassword(AuthUser authUser) {
		Optional<User> optional=userDao.findByUsernameAndPassword(authUser.getUsername(),authUser.getPassword());
		if(optional.isEmpty())
			throw UserNotFoundException.builder().message("Invalid Credentials...Invalid Username or Password").build();
		return ResponseEntity.status(HttpStatus.OK).body(ResponseStructure.builder().httpStatus(HttpStatus.OK.value()).message("Found based on username and password").body(optional.get()).build());
	}

	@Override
	public String deletUserById(int id) {
		 Optional<User> u = userDao.findUserById(id);
		 if(u.isEmpty()) {
			 
		 }
		 User user = u.get();
		 userDao.deleteUserById(id);
		return "deleted successfully";
	}


	@Override
	public ResponseEntity<?> verifyOTP(int id, int otp) {
		Optional<User>optional=userDao.findUserById(id);
		if(optional.isEmpty())
		
			throw new RuntimeException("Invalid User Id unable to verify the OTP");
		User user=optional.get();
		if(otp!=user.getOtp())
			throw new RuntimeException("Invalid OTP unable to verify the OTP");
		user.setStatus(UserStatus.ACTIVE);
		user=userDao.saveUser(user);
		return ResponseEntity.status(HttpStatus.OK).body(ResponseStructure.builder().httpStatus(HttpStatus.OK.value()).
				message("OTP Verified Successfully").body(user).build());
	}
	
	
}












