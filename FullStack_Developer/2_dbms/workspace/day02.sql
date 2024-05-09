use gb;
create table test(
	pk varchar(300) primary key,
    num1 int,
    num2 decimal(5,2),
    str1 varchar(300),
	str2 varchar(300)
);

#데이터 추가
#컬럼명을 지정해서 추가시 해당 컬럼에 들어갈 값들만 넘겨주기(컬럼 순서가 바뀌면 바뀐 순서대로 데이터 작성)
insert into test (pk,num1,str1) values('pk001',10,111.11);
#PK값이 동일하면 제약조건 충돌로 데이터 삽입 불가
insert into test (num1,pk) values(10,'pk001');
insert into test (num1,pk) values(10,'pk002');
#컬럼명을 생략할 시 테이블 구성 컬럼 순서대로 값을 모두 넘겨주기
insert into test values('pk003',30,222.22,'varchar data1','varchar data2');

#데이터 삭제
delete from test where pk='pk002';
#Edit > Preferences > SQL Editor > 맨 아래의 Safe Updates 해제
#해야지만 PK가 아닌 조건식으로도 수정, 삭제 가능
delete from test where num1>=0 and num1<=10;
#컬럼명 between 값1 and 값2 : 값1과 값2 사이인지 확인하는 조건식
delete from test where num1 between 0 and 10;

delete from test where num1=10 or num1=30;
delete from test where num1 in (10,30);

#데이터 수정
update test set num1=300 where pk='pk003';
update test set num1=100, str1='changed msg' where pk='pk001';

#데이터 조회
select pk, num1, str1 from test where num1>100;
select pk, num2 from test;
#문자열 대소비교 가능(사전순으로 뒤에 있는 값이 더 큰 값)
select * from test where pk>'pk001';

select * from test;

#컬럼명 자리에 연산이나 함수 호출을 써주게 되면 from 뒤에 적힌 테이블의 행의 개수만큼 결과가 나온다.(where 조건절이 참인 행의 개수)
select 1+1 from test where pk>'pk001';
#dual 테이블 : 간단한 값이나 연산의 결과를 검색하기 위한 내장 테이블, 결과가 한 행으로만 나옴
select now() from dual;

#별칭(Alias) : 컬럼명 혹은 테이블에 이름 대신 사용할 별칭을 주는 기법
select 1+2*37/14-117+0.24*17.24 result from dual;
#별칭이 키워드거나 별칭에 띄어쓰기가 포함된 경우에는 쌍따옴표로 묶어준다.
select 1+2*37/14-117+0.24*17.24 "re sult" from dual;
#컬럼명에 별칭을 줄 때에는 as 키워드 사용 가능
select 1+2*37/14-117+0.24*17.24 as "re sult" from dual;

select num2 from test;
select test.num2 from gb.test;
select t.num2 from gb.test t;

#수강신청 관리 시스템
#학생테이블
#학번	이름		학과		주민번호	현재학년
#1000	김사과	컴공과	111111	2

#과목테이블
#과목명(PK)	과목설명	분류
#미적분학	?????	전공기초

#수업테이블
#수업코드(PK)	과목명(FK)	요일	시간	교수번호(FK)	개설학과(FK)	정원	강의실

#학과테이블
#학과코드(PK)	학과명	학과사무실 위치		학과사무실 번호

#교수테이블
#교수번호(PK)	교수명	나이	핸드폰번호	학과코드(FK)

#신청테이블
#학번(FK)	수업코드(FK)	신청일시

#인천공항 수하물 관리 시스템
#K-리그(축구) 홈페이지 제작

#꽃 테이블에 데이터 4개 삽입하기
select * from flower;
insert into flower values('f001','민들레','하얀색',2000,10);
insert into flower values('f002','장미','하얀색',10000,5);
insert into flower values('f003','장미','주황색',15000,5);
insert into flower values('f004','무궁화','민트색',20000,3);

#영화 테이블에 데이터 3개 삽입하기
select * from movie;
insert into movie values('m001','서울의겨울','코미디','2h13m',8.5,'박민수,신재빈,이예나');
insert into movie values('m002','노량진','액션','1h58m',8.7,'민동민,박소은,정상현');
insert into movie values('m003','괴물','코미디','2h7m',7.5,'김일환,신재빈,정재룡');

#영화 테이블에 있는 모든 데이터들의 장르를 액션으로 수정하기
update movie
set genre = '액션';

#주문 테이블에 있는 특정 화분을 이용한 주문들의 주문자를 김사과로 수정하기
select * from `order`;
select * from flower;
select * from pot;
insert into pot values('p001','원형','파란색','비브라늄',10000000,2);
insert into pot values('p002','별모양','벽돌색','벽돌',20000,10);

insert into `order` values('반하나','01012341234','서울시 강남구 대치동','f001','p001');
insert into `order` values('이체리','01011111111','서울시 동작구 사당동','f001','p002');
insert into `order` values('이체리','01011111111','서울시 동작구 사당동','f002','p002');
insert into `order` values('이체리','01011111111','서울시 동작구 사당동','f003','p001');

update `order`
set name = '김사과'
where pot_id='p001';

#화분 테이블에 있는 모든 데이터 삭제하기
delete from `order`;
delete from pot;

use world;
select * from country;



