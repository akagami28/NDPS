USE `aipay`;
DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
	`user_id` int NOT NULL,
    `name` varchar(10) NOT NULL,
    `password` varchar(100) NOT NULL,
    `role` varchar(100) NOT NULL,
    
    primary key (`user_id`)
) ENGINE=InnoDB DEFAULT charset=latin1;

INSERT INTO `user` VALUES
(1,"akhil","test123","ROLE_ADMIN"),
(2,"rahul","test123","ROLE_MANAGER"),
(3,"ravi","test123","ROLE_DEVELOPER");


