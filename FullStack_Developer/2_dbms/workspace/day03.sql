use gb;
#database : gb
drop table test;
create table test(
	num int primary key auto_increment,
    strdata varchar(300),
    timedata datetime
);
select * from test;
insert into test (strdata) values('Hello0');
insert into test (strdata) values('Hello1');
insert into test (num, strdata) values(100,'Hello2');
insert into test (strdata) values('Hello3');

#date, datetime타입
insert into test (timedata) values('2012-12-31 17:31:24');
insert into test (timedata) values(now());

drop table product;
drop table user;
create table user(
	userid varchar(300) primary key,
    userpw varchar(300) not null,
    username varchar(300) not null,
    userage int,
    userphone varchar(300) not null,
    useraddr varchar(1000),
    regdate datetime default now()
);
insert into user(userid, userpw, username, userage, userphone, useraddr)
values('apple','abcd1234','김사과',10,'01012341234','서울시 강남구 역삼동');

use world;
#database : world

#문자열 연결, 문자열 길이
select concat('He','llo',' DBMS'), length('flower') from dual;
#올림, 내림, 반올림
select ceil(3.14), floor(3.14), round(3.45,1) from dual;
#형변환함수
select cast(10 as char), convert(10,char) from dual;
#null처리 함수
select name, gnp, ifnull(gnpold,'기록없음') gnpold from country;

#그룹함수
select count(*), sum(population), max(population), min(population), avg(population) from country;
select count(*), count(gnpold) from country;
select count(ifnull(gnpold,'얍')) from country;

#order by 절
select name, population, gnp from country order by gnp desc;
#컬럼의 번호로 정렬 가능
select name, population, gnp from country order by 3 desc;
select name, population, gnp, gnp/population "인당 gnp" from country order by 4 desc;
#컬럼의 별칭으로 정렬 가능
select name, population, gnp, gnp/population "인당 gnp" from country order by `인당 gnp` desc;

#group by
select * from country;
select continent, count(*) from country;
select continent, count(*) from country group by continent;
select continent, region, count(*) from country group by continent, region;

#having 절
select * from country;
select continent, region, count(*) from country
group by continent, region having count(*) >= 10;
select * from country;
#소속 대륙이 Asia인 나라 검색
select * from country where continent = 'Asia';
#소속 대륙이 Europe이 아닌 나라 검색
select * from country where continent != 'Europe';
#인구수가 1000만 이하인 나라 검색
select * from country where population <= 10000000;
#인구수가 7000만 이상인 나라 검색
select * from country where population >= 70000000;
#인구수는 4500만 이상이면서 넓이(SurfaceArea)가 10만제곱km 이하인 나라 검색
select * from country where population >= 45000000 and surfacearea <= 100000;
#소속 대륙은 Asia이고 건국 연도(IndepYear)가 1948인 나라 검색
select * from country where continent = 'Asia' and indepyear = 1948;
#지역이 동아프리카 이고 건국 연도가 1970년~1980년 인 나라 검색
select * from country where region = 'Eastern Africa' and indepyear between 1970 and 1980;
#이름에 North가 들어간 나라들 검색
select * from country where name like('%North%');
#Asia 대륙에 속하고 끝이 a로 끝나는 나라들 검색
select * from country where continent = 'Asia' and name like('%a');
#Europe 대륙에 속하고 이름 글자수가 6글자이며 f가 들어가는 나라들 검색
select * from country where continent = 'Europe' and length(name) = 6 and name like ('%f%');
select * from country where continent = 'Europe' and name like('______') and name like ('%f%');
#대륙별 평균 gnp 검색
select continent,avg(gnp) from country group by continent;
#gnp 평균이 100000 이상인 지역들의 지역명, gnp 최대값, gnp 최소값, gnp 평균 검색
select region, max(gnp), min(gnp), avg(gnp)
from country group by region having avg(gnp)>=100000;
#대륙별 평균 인구를 검색하되 15000000명을 넘는 대륙만 검색
select continent, avg(population)
from country group by continent having avg(population) >= 15000000;
#인구수가 20000000을 넘는 나라들의 평균 넓이가 2000000제곱km를 넘는 대륙들만 검색
select continent, avg(surfacearea)
from country where population >= 20000000
group by continent having avg(surfacearea)>2000000;
#대륙별, 지역별로 나라들의 평균 수명 검색
select continent, region, avg(lifeexpectancy) from country group by continent, region;
#아시아, 유럽에 속한 나라들 중에 정치제도(governmentform)별로 정치제도, 나라수, 평균수명, 평균gnp 검색
select governmentform, count(*), avg(lifeexpectancy), avg(gnp)
from country where continent in ('Asia','Europe') group by governmentform;














