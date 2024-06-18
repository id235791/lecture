use gb;
create table ums_user(
	userid varchar(300) primary key,
    userpw varchar(300),
    username varchar(300),
    usergender varchar(300),
    zipcode varchar(300),
    addr varchar(1000),
    addrdetail varchar(1000),
    addretc varchar(300),
    userhobby varchar(1000)
);
create table ums_product(
	prodnum int primary key auto_increment,
    prodname varchar(1000),
    prodprice int,
    prodamount int,
    prodinfo varchar(4000),
    prodcategory varchar(300),
    likecnt int default 0,
    regdate datetime default now(),
    userid varchar(300)
);
insert into ums_product (prodname,prodprice,prodamount,prodinfo,prodcategory,userid)
values('볼펜',2500,1000,'누군가가 공격할 때 방어하세요','스포츠/레저','banana');
insert into ums_product (prodname,prodprice,prodamount,prodinfo,prodcategory,userid)
values('연필',1000,3000,'잘 안써집니다','문구/오피스','apple');
insert into ums_product (prodname,prodprice,prodamount,prodinfo,prodcategory,userid)
values('지우개',2000,3000,'잘 안지워집니다','문구/오피스','apple');
insert into ums_product (prodname,prodprice,prodamount,prodinfo,prodcategory,userid)
values('축구공',10000,500,'물이 가득 차있는 축구공입니다.','스포츠/레저','banana');
insert into ums_product (prodname,prodprice,prodamount,prodinfo,prodcategory,userid)
values('세제',4000,3000,'세탁하려고 산건데 세탁물을 만들어줘요','생활용품','banana');
insert into ums_product (prodname,prodprice,prodamount,prodinfo,prodcategory,userid)
values('에어팟 프로4',400000,50,'그냥 국산 쓰세요. 삼성이 최곱니다.','가전디지털','apple');

insert into ums_product (prodname,prodprice,prodamount,prodinfo,prodcategory,userid)
(select prodname,prodprice,prodamount,prodinfo,prodcategory,userid from ums_product);
drop table ums_product;

select * from ums_product order by prodnum desc;

