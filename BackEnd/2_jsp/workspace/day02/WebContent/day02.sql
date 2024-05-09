drop database gb;
create database gb;
use gb;
create table user(
	userid varchar(300) primary key,
    userpw varchar(300),
    username varchar(300)
);
drop table user;

select * from user where userid = 'apple';


