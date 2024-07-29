package com.ndps.ots.security.config.user;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
//@Builder
@Entity
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id")
	private int id;
	
	@Column(name = "name")
	private String username;
	private String password;
	private String role;
	
//	USE `aipay`;
//	DROP TABLE IF EXISTS `user`;
//
//	CREATE TABLE `user` (
//		`user_id` int NOT NULL,
//	    `name` varchar(10) NOT NULL,
//	    `password` varchar(100) NOT NULL,
//	    `role` varchar(100) NOT NULL,
//	    
//	    primary key (`user_id`)
//	) ENGINE=InnoDB DEFAULT charset=latin1;
//
//	INSERT INTO `user` VALUES
//	(1,"akhil","test123","ROLE_ADMIN"),
//	(2,"rahul","test123","ROLE_MANAGER"),
//	(3,"ravi","test123","ROLE_DEVELOPER");
	
}
