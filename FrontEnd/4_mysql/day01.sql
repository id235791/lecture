-- 데이터베이스 확인
show databases;

-- 데이터베이스 생성
-- CREATE DATABASE 데이터베이스명;
CREATE DATABASE frontend;

-- 데이터베이스 삭제
-- DROP DATABASE 데이터베이스명;
DROP DATABASE frontend;

-- 데이터베이스 사용
-- USE 데이터베이스명;
USE frontend;


-- 테이블(table)
-- 데이터를 행과 열로 스키마에 따라 저장할 수 있는 구조

-- 스키마
-- 데이터베이스의 구조와 제약 조건에 관한 명세를 기술한 집합

-- CREATE TABLE 테이블명(
-- 필드명1 필드타입 제약조건,
-- 필드명2 필드타입 제약조건,
-- ...
-- ) 

-- 데이터 타입

-- 숫자 타입
-- 정수 : tinyint, smallint, mediumint, int, bigint ...
-- 소수 : float, double

-- 문자 타입
-- 텍스트 : char, varchar, text
-- char(20) -> 1byte만 저장하더라도 총 20byte를 모두 소모
-- varchar(20) -> 1byte를 저장하면 총 1byte를 소모. 65535byte까지 저장
-- 바이너리 : binary, varbinary
-- 열거 : enum('남자', '여자')

-- 날짜와 시간타입
-- 날짜 : date
-- 날짜와 시간타입 : datetime, timestamp(1970년 1월1일 0시0분0초 ~)







