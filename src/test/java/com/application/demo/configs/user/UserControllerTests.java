package com.application.demo.configs.user;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;

import com.ndps.ots.security.config.user.User;
import com.ndps.ots.security.config.user.UserController;
import com.ndps.ots.security.config.user.UserService;

public class UserControllerTests {

	@Mock
	UserService userService;
	
	@InjectMocks
	UserController userController;
	
	User user1,user2;
	String tracerId;
	List<User> users = new ArrayList<>();
	
	@BeforeEach
	void setUp()
	{
		MockitoAnnotations.openMocks(this);
		user1 = new User(1, "akhil", "test123", "ROLE_ADMIN");
		user2 = new User(2, "akagami", "test123", "ROLE_MANAGER");
		users.add(user1);
		users.add(user2);
		tracerId = UserController.tracerId;
	}
	
	@Test
	void testSaveUser()
	{
		when(userService.saveUser(user1, tracerId)).thenReturn(user1);
		assertThat(HttpStatus.CREATED)
			.isEqualTo(userController.saveUser(user1).getStatusCode());
	}
	
	@Test
	void testGetAllUsers()
	{
		when(userService.getAllUsers(tracerId)).thenReturn(users);
		assertThat(HttpStatus.OK)
			.isEqualTo(userController.getAllUsers().getStatusCode());
	}
	
	@Test
	void testUpdateUser()
	{
		when(userService.updateUser(user1, tracerId)).thenReturn(user1);
		assertThat(HttpStatus.ACCEPTED)
			.isEqualTo(userController.updateUser(user1).getStatusCode());
	}
	
	@Test
	void testDeleteAll()
	{
		doNothing().when(userService).deleteAllUsers(tracerId);
		assertThat(HttpStatus.OK)
			.isEqualTo(userController.deleteAll().getStatusCode());
	}
	
	@Test
	void testDeleteById()
	{
		when(userService.deleteUserById(1, tracerId)).thenReturn(user1);
		assertThat(HttpStatus.OK)
			.isEqualTo(userController.deleteById(1).getStatusCode());
	}
}
