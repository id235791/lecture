use testworld;
DROP TABLE test;
CREATE TABLE test(
	num INT AUTO_INCREMENT,
	strdata VARCHAR(300),
	timedata DATETIME,
	CONSTRAINT PRIMARY KEY(num)
);
INSERT INTO test (strdata) values('Hello0');
INSERT INTO test (num,strdata) values(100,'Hello1');
INSERT INTO test (strdata) values('Hello2');
INSERT INTO test (strdata) values('Hello3');
DELETE FROM test WHERE num = 102;
INSERT INTO test (num,strdata) values(1000,'Hello4');
INSERT INTO test (strdata) values('Hello5');
select * from test;

INSERT INTO test (timedata) values('2000-01-01 11:11:11');

SELECT num AS 정수데이터 FROM test;
SELECT timedata 시간데이터 FROM test;

USE WORLD;
select * from country;
#이름에 North가 들어간 나라들 검색
SELECT * FROM country WHERE name LIKE('%North%');
#Asia 대륙에 속하고 끝이 a 로 끝나는 나라들 검색
SELECT * FROM country WHERE continent = 'Asia' AND name LIKE('%a');
#Europe 대륙에 속하고 이름 글자수가 5글자이며 f가 들어가는 나라들 검색
SELECT * FROM country WHERE continent = 'Europe' AND name LIKE('_____') AND name LIKE('%a%'); 
