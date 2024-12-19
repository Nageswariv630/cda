package org.jsp.cda.controller;
import org.jsp.cda.entity.AuthUser;
import org.jsp.cda.entity.User;
import org.jsp.cda.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
@CrossOrigin
@RestController
@RequestMapping(value="/users")
public class UserController {
	@Autowired
	private UserService service;
	
	@Operation(summary = "This API will do login validation",description = "This API will do login validation by accepting username and password as states")
	@ApiResponses(value = {@ApiResponse(responseCode = "200",description = "User Found Successfully"),@ApiResponse(responseCode = "400",description = "No User Found")})
	@PostMapping(value="/login")
	public ResponseEntity<?> findByUsernameAndPassword(@RequestBody AuthUser authUser){
		return service.findByUsernameAndPassword(authUser);
	}
	
	@Operation(summary = "This API will save the Data" ,description = "This api will save the data which you are given will save to the database")
	@ApiResponses(value = {@ApiResponse(responseCode = "200",description = "User Found Successfully"),@ApiResponse(responseCode = "400",description = "No User Found")})
	@PostMapping
	public ResponseEntity<?> saveUser(@RequestBody User user){
		return service.saveUser(user);
	}
	
	@Operation(summary = "THis API will find based on Id",description = "THis API will find based on Id from the table User")
	@ApiResponses(value = {@ApiResponse(responseCode = "200",description = "User Found Successfully"),@ApiResponse(responseCode = "400",description = "No User Found")})
	
	@GetMapping(value="/{id}")
	public ResponseEntity<?> findUserById(@PathVariable int id){
		return service.findUserById(id);
	}
	@GetMapping
	public ResponseEntity<?> findAllUsers(){
		return service.findAllUsers();
	}
	@DeleteMapping("/id/{id}")
	public String deleteUserById(@PathVariable int id) {
		return service.deletUserById(id);
	}
	@PatchMapping("/{id}/otp/{otp}")
	public ResponseEntity<?>verifyOTP(@PathVariable int id,@PathVariable int otp){
		return service.verifyOTP(id,otp);
		
	}
	@GetMapping("/email/{email}")
	public ResponseEntity<?>findUserByEmail(@PathVariable String email){
		return service.findUserByEmail(email);
	}

}






