CREATE TABLE `forge`.`users` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `password` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`));


create table users
(
  id int auto_increment,
  name varchar(255) not null,
  password varchar(100) not null,
  role enum('user', 'manager', 'admin') not null,
  constraint users_pk
    primary key (id)
);

create unique index users_name_uindex
  on users (name);