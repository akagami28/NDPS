package com.application.demo.configs.user;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.ndps.ots.security.config.user.User;
import com.ndps.ots.security.config.user.UserController;
import com.ndps.ots.security.config.user.UserRepository;
import com.ndps.ots.security.config.user.UserService;

public class UserServiceTests {

	@Mock
	private UserRepository userRepository;
	
	@Mock
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@InjectMocks
	private UserService userService;
	
	@SuppressWarnings("unused")
	private AutoCloseable autoCloseable;
	
	User user1, user2;
	String tracerID;
	List<User> users;
	
	@BeforeEach
	void setUp()
	{
		autoCloseable = MockitoAnnotations.openMocks(this);
		
		user1 = new User(1, "akhil", "test123", "ROLE_ADMIN");
		user2 = new User(2, "akagami", "test123", "ROLE_MANAGER");
		users = new ArrayList<>();
		users.add(user1);
		users.add(user2);
		
		tracerID = UserController.tracerId;
	}
	
	@Test
	void testSaveUser()
	{
		when(userRepository.save(Mockito.any(User.class))).thenReturn(user1);
		user1.setPassword(bCryptPasswordEncoder.encode(user1.getPassword()));
		assertThat(userService.saveUser(user1, tracerID)).isNotNull();
	}
	
	@Test
	void testGetAllUsers()
	{
		when(userRepository.findAll()).thenReturn(users);
		assertThat(userService.getAllUsers(tracerID).get(0).getId()).isEqualTo(user1.getId());

	}
	
	@Test
	void testUpdateUser()
	{
		when(userRepository.findById(2)).thenReturn(Optional.ofNullable(user1));
		user1.setId(user2.getId());
		user1.setUsername(user2.getUsername());
		user1.setPassword(bCryptPasswordEncoder.encode(user2.getPassword()));
		user1.setRole(user2.getRole());
		when(userRepository.save(Mockito.any(User.class))).thenReturn(user1);
		assertThat(userService.updateUser(user2, tracerID)).isNotNull();
	}
	
	@Test
	void testDeleteUserById()
	{
		when(userRepository.findById(1)).thenReturn(Optional.ofNullable(user1));
		doNothing().when(userRepository).delete(user1);
		assertAll(()-> userService.deleteUserById(1, tracerID));
	}
	
	@Test
	void testDeleteAll()
	{
		doNothing().when(userRepository).deleteAll();
		assertAll(()-> userService.deleteAllUsers(tracerID));
	}
	
	@AfterEach
	void tearDown()
	{
		user1=null;
		user2=null;
		autoCloseable = null;
		users = null;
		tracerID=null;
	}
}
