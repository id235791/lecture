USE testworld;
#DML
DROP TABLE TEST;
CREATE TABLE TEST(
	PK VARCHAR(300) PRIMARY KEY,
	NUM1 INT,
	NUM2 DECIMAL(5,2),
	STR1 VARCHAR(300),
	STR2 VARCHAR(300)
);
#데이터 추가
#컬럼명 생략시 테이블 구성 컬럼 순서대로 값 넘겨주기
INSERT INTO TEST VALUES('PK001',10000,100.32,'VARCHAR DATA','CHAR DATA');

#PK값이 동일하면 UNIQUE 제약조건 충돌로 데이터 삽입 불가
INSERT INTO TEST VALUES('PK001',15000,111.11,'VARCHAR DATA','CHAR DATA');
#컬럼명을 지정해주면 해당 컬럼에 들어갈 값들만 넘겨주기(컬럼 순서 바꾸면 바뀐 순서대로 데이터 작성)
INSERT INTO TEST (PK,NUM1,STR1) VALUES('PK002',20000,'VARCHAR DATA');

#데이터 수정
UPDATE TEST
SET NUM2 = 222.22
WHERE PK = 'PK002';

#데이터 삭제
DELETE FROM TEST
WHERE NUM1<=100000 AND NUM1>=0;
#컬럼명 BETWEEN 값1 AND 값2 : 컬럼명 >= 값1 AND 컬럼명 <= 값2
DELETE FROM TEST
WHERE NUM1 BETWEEN 0 AND 100000;

DELETE FROM TEST
WHERE PK = 'PK001' OR PK = 'PK002';
#컬럼명 IN (값1,값2) : 컬럼명 = 값1 OR 컬럼명 = 값2
#(컬럼1,컬럼2) IN (값1,값2) : 컬럼의 개수와 뒤에오는 값의 개수가 같다면 뒤에 올 모든 쌍들 중에
#해당 컬럼으로 검색되는 값들이 같이 존재한다면 참
DELETE FROM TEST
WHERE PK IN ('PK001','PK002');

#데이터 검색
SELECT PK,NUM2 FROM TEST;
#* : 전부라는 뜻
SELECT * FROM TEST;

SELECT * FROM TEST WHERE NUM1>10000;
#문자열 대소비교 가능(사전순으로 뒤에 있는것이 더 큰값)
SELECT * FROM TEST WHERE PK>'PK001';
#조건에 해당하는 행이 없을 때
SELECT * FROM TEST WHERE PK='PK003';

#DUAL 테이블 : 간단한 값이나 연산의 결과를 검색하기 위한 내장 테이블
SELECT 1+1 FROM DUAL;

#AS(ALIAS) : 별칭
SELECT 1+2*37/124-117+0.147*7 RESULT FROM DUAL;
#별칭이 키워드거나 별칭에 띄어쓰기가 포함된 경우에는 쌍따옴표로 묶어준다.
SELECT 1+2*37/124-117+0.147*7 "RE SULT" FROM DUAL;
SELECT TEST.PK FROM TEST;
#테이블명에 별칭주기
SELECT t.PK FROM TEST t;

#샘플데이터 확인
use world;
SELECT * FROM city;
SELECT * FROM country;
SELECT * FROM countrylanguage;

#country 테이블
#소속 대륙(Continent)이 Asia인 나라 검색
#소속 대륙(Continent)이 Europe이 아닌 나라 검색
#인구수(Population)가 1000만 이하인 나라 검색
#인구수(Population)가 7000만 이상인 나라 검색
#인구수(Population)는 4500만 이상이면서 넓이(SurfaceArea)가 10만제곱km이하인 나라 검색
#소속 대륙(Continent)은 Asia 이고 건국 연도(IndepYear)가 1948인 나라 검색
#지역(Region)이 동아프리카(Eastern Africa) 이고 건국 연도 가 1970~1980인 나라 검색

