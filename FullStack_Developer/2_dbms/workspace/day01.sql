#주석
#실행 : 실행할 명령어에 커서를 두고 Ctrl+Enter
create database gb;
drop database gb;

use gb;

#자동차 테이블
#차번호, 모델, 색깔, 가격
#테이블 생성
create table car(
	carnum varchar(300),
    model varchar(300),
    color varchar(300),
    price int
);

#테이블 삭제
drop table car;

#테이블 수정
#컬럼 추가
alter table car add(owner varchar(300));
#컬럼 자료형 수정
alter table car modify owner varchar(301);
#컬럼 삭제
alter table car drop owner;

#테이블을 수정하기보단, drop하고 새로운 형태의 create문을 다시 수행
create table car(
	carnum varchar(300),
    model varchar(300),
    color varchar(300),
    price int,
    owner varchar(300)
);

drop table car;

#Primary Key
create table car(
	carnum varchar(300) primary key,
	model varchar(300),
    color varchar(300),
    price int
);
create table car(
	carnum varchar(300),
	model varchar(300),
    color varchar(300),
    price int,
    constraint primary key(carnum)
);

#Foreign Key
create table user(
	userid varchar(300) primary key,
    username varchar(300)
);

create table product(
	prodnum int primary key,
    prodname varchar(300),
    prodprice int,
    userid varchar(300),
    constraint p_u_fk foreign key(userid) references user(userid)
);

#각 테이블당 컬럼은 최소 3 ~ 4개
#동물 테이블
#check 제약조건
#check(조건식) --> 조건식이 참인 데이터들만 삽입 가능
#컬럼 in (값1, 값2, ...) : 컬럼의 값이 값1 또는 값2 또는 ... 이면 참
create table animal(
	animal_id varchar(300) primary key,
	name varchar(300),
    gender char(1) check(gender in ('M','W')),
    age int
);
#학생 테이블
#unique 제약조건 : 중복되는 값이 들어오면 오류 발생
create table student(
	studno varchar(300) primary key,
	name varchar(300),
	grade int,
    major varchar(300),
    jumin varchar(300) unique
);
#꽃집에서 사용할 프로그램에 필요한 테이블
create table flower(
	flower_id varchar(300) primary key,
	name varchar(300),
    color varchar(300),
    price int,
    amount int
);
create table pot(
	pot_id varchar(300) primary key,
	shape varchar(300),
    color varchar(300),
    material varchar(300),
    price int,
    amount int
);
create table `order`(
	name varchar(300),
    phone varchar(300),
    addr varchar(300),
    flower_id varchar(300),
    pot_id varchar(300),
    constraint order_flower_fk foreign key(flower_id) references flower(flower_id),
    constraint order_pot_fk foreign key(pot_id) references pot(pot_id)
);

#CGV에서 사용할 사내 프로그램에 필요한 테이블
create table movie(
	movie_id varchar(300) primary key,
	title varchar(300),
    genre varchar(300),
    running_time varchar(300),
    score decimal(3,1),
	actor varchar(300)
);
create table theater(
	theater_code varchar(300) primary key,
	location varchar(300),
    num varchar(300),
    seat_count int
);
create table time_table(
	theater_code varchar(300),
    movie_id varchar(300),
    start_time datetime,
    end_time datetime,
    constraint theater_fk foreign key(theater_code) references theater(theater_code),
    constraint movie_fk foreign key(movie_id) references movie(movie_id)
);


#애견샵에서 사용할 프로그램에 필요한 테이블




