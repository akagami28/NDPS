package com.ndps.ots.security.config.user;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ndps.ots.utility.Constant;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @author AkhilK
 * This Controller contains CRUD operation for User Credentials in Spring Boot.
 */
@Slf4j
@RestController
@RequestMapping("/api/security/user")
public class UserController {

	@Autowired
	private UserService userService;
	
	public final static String tracerId = String.valueOf(System.currentTimeMillis());
	
	/**
	 * Calls the saveUser() in Service
	 * @param user
	 * @return ResponseEntity<String>
	 */
	@PostMapping("/")
	public ResponseEntity<String> saveUser(@RequestBody User user)
	{
		log.info("{}_{} Inside saveUser() - Request >> {} ",Constant.SERVICE_3,tracerId,user);
		User user2 = userService.saveUser(user,tracerId);
		log.info("{}_{} Inside saveUser() - Response >> {} ",Constant.SERVICE_3,tracerId,user2);
		return new ResponseEntity<>("User saved successfully \nStatus: "+HttpStatus.CREATED, HttpStatus.CREATED);
	}
	
	/**
	 * Calls the getAllUsers() present in Service which returns the list of users.
	 * @return ReponseEntity<String>
	 */
	@GetMapping("/")
	public ResponseEntity<String> getAllUsers()
	{
		log.info("{}_{} Inside getAllUsers() ",Constant.SERVICE_3,tracerId);
		List<User> users = userService.getAllUsers(tracerId);
		List<Integer> userIds = users
								.stream()
								.map(x -> x.getId())
								.collect(Collectors.toList());
		log.info("{}_{} Inside getAllUsers() - Response >> {} ",Constant.SERVICE_3,tracerId,userIds);
		
		return new ResponseEntity<String>("ID List: "+userIds, HttpStatus.OK);
	
	}
	
	/**
	 * Calls the method updateUser() in Service which updates the user details.
	 * @param user
	 * @return ResponseEntity<String>
	 */
	@PutMapping("/")
	public ResponseEntity<String> updateUser(@RequestBody User user)
	{
		log.info("{}_{} Inside updateUser() - Request >> {} ",Constant.SERVICE_3,tracerId,user);
		User user2 = userService.updateUser(user,tracerId);
		log.info("{}_{} Inside updateUser() - Response >> {} ",Constant.SERVICE_3,tracerId,user2);
		return new ResponseEntity<String>("User updated with Id: "+user2.getId()+"\nStatus: "+HttpStatus.ACCEPTED, HttpStatus.ACCEPTED);
	}
	
	/**
	 * Calls the method deleteAllUsers() in Service which deletes all users.
	 * @return Response<String>
	 */
	@DeleteMapping("/")
	public ResponseEntity<String> deleteAll()
	{
		log.info("{}_{} Inside deleteAll() ",Constant.SERVICE_3,tracerId);
		userService.deleteAllUsers(tracerId);
		return new ResponseEntity<String>("User deleted successfully \nStatus: "+HttpStatus.OK, HttpStatus.OK);
	}
	
	/**
	 * Calls the deleteUserById() in Service which deletes user based on id.
	 * @param id
	 * @return ResponseEntity<String>
	 */
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteById(@PathVariable Integer id)
	{
		log.info("{}_{} Inside deleteById() - Request >> {} ",Constant.SERVICE_3,tracerId,id);
		User user = userService.deleteUserById(id,tracerId);
		return new ResponseEntity<String>("User deleted with details: "+user+"\nStatus: "+HttpStatus.OK,HttpStatus.OK );
	}
}
