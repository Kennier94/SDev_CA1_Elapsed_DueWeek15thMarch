# --- !Ups
delete from user;

insert into user (email,name,password,role) values ( 'mark@company.com', 'Mark Admin', 'password', 'admin');
insert into user (email,name,password,role) values ( 'craig@company.com', 'Craig Manager', 'password', 'manager');
insert into user (email,name,password,role) values ( 'staff@company.com', 'Sean Staff', 'password', 'staff');