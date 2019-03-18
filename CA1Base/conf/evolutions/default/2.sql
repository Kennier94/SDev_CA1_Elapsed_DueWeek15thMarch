# --- Sample dataset
 
# --- !Ups

delete from employee;
delete from project;
delete from department;
 
insert into department (id,name) values ( 1,'Computing' );
insert into department (id,name) values ( 2,'Marketing' );
insert into department (id,name) values ( 3,'HR' );
insert into department (id,name) values ( 4,'PR' );
insert into department (id,name) values ( 5,'Management' );
insert into department (id,name) values ( 6,'Admin' );

insert into project (id,name) values ( 1,'Project A' );
insert into project (id,name) values ( 2,'Project B' );
insert into project (id,name) values ( 3,'Project C' );
insert into project (id,name) values ( 4,'Project D' );
insert into project (id,name) values ( 5,'Project E' );
insert into project (id,name) values ( 6,'ProjectF' );
 
insert into employee (id,department_id,name,age,years_worked) values ( 1,1,'Mark',43, 20);
insert into employee (id,department_id,name,age,years_worked ) values ( 2,2,'Craig',27,5);
insert into employee (id,department_id,name,age,years_worked) values ( 3,3,'Ellen',33,12);
insert into employee (id,department_id,name,age,years_worked) values ( 4,4,'Anne',40,22);
insert into employee (id,department_id,name,age,years_worked) values ( 5,5,'Pat',30,9);
insert into employee (id,department_id,name,age,years_worked) values ( 6,4,'Charles',28,10);


