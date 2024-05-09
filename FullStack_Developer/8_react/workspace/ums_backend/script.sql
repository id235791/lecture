create database gb;
use gb;

create table ums_user(
	useridx int primary key auto_increment,
    userid varchar(300) unique not null,
    userpw varchar(300) not null,
    username varchar(300) not null,
    usergender varchar(300),
	zipcode varchar(300) not null,
    addr varchar(1000),
    addrdetail varchar(1000) not null,
    addretc varchar(300),
    userhobby varchar(2000)
);

select * from ums_user;

create table ums_product(
	prodnum bigint primary key auto_increment,
    prodname varchar(300) not null,
    prodprice int not null,
    prodamount int not null,
    prodinfo text not null,
    prodcategory varchar(300),
    likecnt int default 0,
    regdate datetime default now(),
    userid varchar(300) not null
);

select * from ums_product;

insert into ums_product (prodname,prodprice,prodamount,prodinfo,prodcategory,userid)
values('볼펜',2000,1000,'볼펜으로 찌르면 매우 아파요','생활용품','apple');
insert into ums_product (prodname,prodprice,prodamount,prodinfo,prodcategory,userid)
values('볼펜',2500,500,'누군가가 공격할 때 방어하세요','스포츠/레저','banana');
insert into ums_product (prodname,prodprice,prodamount,prodinfo,prodcategory,userid)
values('연필',1500,1500,'지우개로 잘 안지워집니다','문구/오피스','banana');
insert into ums_product (prodname,prodprice,prodamount,prodinfo,prodcategory,userid)
values('네임펜',2000,1000,'이름 쓰기에 딱 좋은 펜입니다','문구/오피스','apple');
insert into ums_product (prodname,prodprice,prodamount,prodinfo,prodcategory,userid)
values('축구공',10000,30,'물이 가득 차있는 축구공입니다','스포츠/레저','apple');
insert into ums_product (prodname,prodprice,prodamount,prodinfo,prodcategory,userid)
values('농구공',10000,30,'돌이 가득 차있는 농구공입니다','스포츠/레저','banana');
insert into ums_product (prodname,prodprice,prodamount,prodinfo,prodcategory,userid)
values('세제',3000,700,'세탁하려고 산건데 세탁물을 만들어줘요','생활용품','banana');