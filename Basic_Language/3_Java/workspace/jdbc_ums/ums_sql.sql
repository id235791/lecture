use gb;
create table user(
	userid varchar(300) primary key,
    userpw varchar(300) not null,
    username varchar(300) not null,
    userage int,
    userphone varchar(300) not null,
    useraddr varchar(1000),
    regdate datetime default now()
);
create table product(
	prodnum int primary key auto_increment,
    prodname varchar(300) not null,
    prodprice int not null,
    prodamount int not null,
    prodinfo varchar(3000) not null,
    likecnt int default 0,
    userid varchar(300),
    constraint p_u_fk foreign key(userid) references user(userid)
);

select * from user where userid='apple';
select * from product;