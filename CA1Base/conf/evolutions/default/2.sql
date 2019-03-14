# --- Sample dataset
 
# --- !Ups

delete from employee;
 delete from department;
 
insert into department (id,name) values ( 1,'Compluting' );
insert into department (id,name) values ( 2,'Marketing' );
insert into department (id,name) values ( 3,'HR' );
insert into department (id,name) values ( 4,'PR' );
insert into department (id,name) values ( 5,'Management' );
insert into department (id,name) values ( 6,'Admin' );
 
insert into employee (id,department_id,name,age,years_worked) values ( 1,1,'Mark',1, 1);
insert into employee (id,department_id,name,age,years_worked ) values ( 2,2,'Craig',1,3);
insert into employee (id,department_id,name,age,years_worked) values ( 3,3,'Ellen',100,2);
insert into employee (id,department_id,name,age,years_worked) values ( 4,4,'Anne',40,1);
insert into employee (id,department_id,name,age,years_worked) values ( 5,5,'Pat',1,99);
insert into employee (id,department_id,name,age,years_worked) values ( 6,4,'Charles',1,5);


