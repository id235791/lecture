--STUDENT PROFESSOR CLASS LIST
INSERT INTO STUDENT (STUNO, STUNAME, STUPHONE, STUADDR)
VALUES('2020001','정다솔','01012341234','서울특별시 강남구 역삼동');

INSERT INTO STUDENT (STUNO,STUNAME)
VALUES('2020002','한동석');

--컬럼명은 생략이 가능하다. 단 생략시에는 모든 컬럼의 값들을 추가해 주어야 한다.
INSERT INTO STUDENT
VALUES('2020003','이용현','01011111111','서울특별시 서초구 방배동');

SELECT STUNAME, STUPHONE FROM STUDENT;
SELECT * FROM STUDENT;

SELECT * FROM STUDENT WHERE STUNO='2020001';

INSERT INTO STUDENT
VALUES('2020004','손서연','01099999999','경기도 안산시');
COMMIT;
ROLLBACK;

UPDATE STUDENT
SET STUADDR='경기도 안양시'
WHERE STUNO='2020001';

TRUNCATE TABLE STUDENT;

INSERT INTO DBMS.CLASS
(CLASSNO, CLASSNAME, CLASSROOM, STARTTIME, PROFNO)
VALUES('A001', 'DBMS', 'J', '0930', '0101');

INSERT INTO DBMS.PROFESSOR
(PROFNO, PROFNAME, "POSITION", PAY)
VALUES('0101', '이순신', '정교수', 6000000);

CREATE SEQUENCE SEQ_LIST
START WITH 1
INCREMENT BY 1
MINVALUE 1
MAXVALUE 1000000;

INSERT INTO LIST
VALUES(SEQ_LIST.NEXTVAL,'A001','2020004');

SELECT * FROM PLAYER;

SELECT * FROM EMP;

-- PLAYER 테이블에서 TEAM_ID가 K01인 선수 검색
SELECT *
FROM PLAYER
WHERE TEAM_ID='K01';
-- PLAYER 테이블에서 TEAM_ID가 K01이 아닌 선수 검색
SELECT * FROM PLAYER WHERE TEAM_ID!='K01';
-- PLAYER 테이블에서 WEIGHT가 80 이하인 선수 검색
SELECT * FROM PLAYER WHERE WEIGHT<=80;
-- PLAYER 테이블에서 HEIGHT 170 이상인 선수 검색
SELECT * FROM PLAYER WHERE HEIGHT>=170;
-- PLAYER 테이블에서 WEIGHT가 70 이상 80 이하인 선수 검색
SELECT * FROM PLAYER WHERE WEIGHT>=70 AND WEIGHT<=80;
-- PLAYER 테이블에서 HEIGHT가 170 이하 이고 WEIGHT가 70 이상인 선수 검색
SELECT * FROM PLAYER WHERE HEIGHT<=170 AND WEIGHT>=70;
-- PLAYER 테이블에서 TEAM_ID가 K03이고 JOIN_YYYY가 2011인 선수 검색
SELECT * FROM PLAYER WHERE TEAM_ID='K03' AND JOIN_YYYY='2011';
-- PLAYER 테이블에서 BIRTH_DATE가 1987년 이후이고 TEAM_ID는 K06인 선수 검색
SELECT * FROM PLAYER
WHERE BIRTH_DATE>TO_DATE('1987-12-31 23:59:59','YYYY-MM-DD HH24:mi:SS');
-- PLAYER 테이블에서 TEAM_ID가 K04 이고 JOIN_YYYY가 2011이거나 2012인 선수 검색
SELECT * FROM PLAYER WHERE TEAM_ID='K04' AND (JOIN_YYYY='2011' OR JOIN_YYYY='2012');
SELECT * FROM PLAYER WHERE TEAM_ID='K04' AND JOIN_YYYY IN ('2011','2012');
---------------------------------------------------------------------------
SELECT * FROM PLAYER;
SELECT * FROM PLAYER WHERE NICKNAME='DBMS';
-- PLAYER 테이블에서 NICKNAME이 없는 선수들 NICKNAME을 DBMS로 바꾸기
UPDATE PLAYER
SET NICKNAME='DBMS'
WHERE NICKNAME IS NULL;
-- PLAYER 테이블에서 POSITION이 DF이고 TEAM_ID가 K04인 선수의 NATION 한국으로 바꾸기
UPDATE PLAYER SET NATION='한국' WHERE "POSITION"='DF' AND TEAM_ID='K04';
SELECT "POSITION", TEAM_ID FROM PLAYER WHERE NATION='한국';
-- PLAYER 테이블에서 BIRTH_DATE가 1985년인 선수중 TEAM_ID가 K01이고 POSITION이 MF인 선수
-- 닉네임을 DBMS2로 바꾸기
UPDATE PLAYER SET NICKNAME='DBMS2'
WHERE TEAM_ID='K01' AND "POSITION"='MF'
	AND (BIRTH_DATE>=TO_DATE('1985-01-01','YYYY-MM-DD')
	AND BIRTH_DATE<=TO_DATE('1985-12-31 23:59:59','YYYY-MM-DD HH24:mi:SS'));











