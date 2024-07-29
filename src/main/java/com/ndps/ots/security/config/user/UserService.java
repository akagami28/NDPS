package com.ndps.ots.security.config.user;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.ndps.ots.utility.Constant;

import lombok.extern.slf4j.Slf4j;

/**
 * This Service contains CRUD operations for User Credentials in Spring Boot.
 * @author AkhilK
 *
 */
@Slf4j
@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	/**
	 * Saves user credential in the database.
	 * @param user
	 * @return User
	 */
	public User saveUser(User user, String tracerid)
	{
		log.info("{}_{} Inside saveUser() - Request >> {} ",Constant.SERVICE_3,tracerid,user);
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		
		return userRepository.save(user);
	}
	
	/*
	 * Retrieves the list of user credentials from the database. 
	 * @return List<User>
	 */
	public List<User> getAllUsers(String tracerid)
	{
		log.info("{}_{} Inside getAllUsers()",Constant.SERVICE_3,tracerid);
		List<User> users = userRepository.findAll();
		log.info("{}_{} Inside getAllUsers() - Response >> {} ",Constant.SERVICE_3,tracerid,users);
		return users;
	}
	
	/**
	 * Updates the user credentials in the database.
	 * @param user
	 * @return User
	 */
	public User updateUser(User user, String tracerid)
	{
		log.info("{}_{} Inside updateUser() - Request >> {} ",Constant.SERVICE_3,tracerid,user);
		User existingUser = userRepository.findById(user.getId()).get();
		existingUser.setId(user.getId());
		existingUser.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		existingUser.setRole(user.getRole());
		existingUser.setUsername(user.getUsername());
		userRepository.save(existingUser);
		log.info("{}_{} Inside updateUser() - Response >> {} ",Constant.SERVICE_3,tracerid,existingUser);
		
		return existingUser;
	}
	
	/**
	 * Deletes user credential based on id.
	 * @param id
	 * @return User
	 */
	public User deleteUserById(Integer id, String tracerid)
	{
		log.info("{}_{} Inside deleteUserById() - Request >> {} ",Constant.SERVICE_3,tracerid,id);
		Optional<User> userOptional = userRepository.findById(1);
		User user = userOptional.get();
		log.info("{}_{} Inside deleteUserById() - Response >> {} ",Constant.SERVICE_3,tracerid,user);
		userRepository.delete(user);
		return user;
	}
	
	/**
	 * Deletes all the user credentials from the database.
	 */
	public void deleteAllUsers(String tracerid)
	{
		log.info("{}_{} Inside deleteAllUsers() ",Constant.SERVICE_3,tracerid);
		userRepository.deleteAll();
	}
}
