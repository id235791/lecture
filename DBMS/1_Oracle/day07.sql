SELECT * FROM T_PROFESSOR;

SELECT PROFNO 교수번호, NAME 이름, PAY 급여,
(SELECT AVG(PAY) FROM T_PROFESSOR) 평균급여
FROM T_PROFESSOR;

--PLAYER 테이블에서 TEAM_ID='K01'인 선수중에 POSITION이 'GK'인 선수
SELECT * FROM PLAYER WHERE TEAM_ID='K01' AND "POSITION"='GK';
SELECT * FROM
	(SELECT * FROM PLAYER WHERE TEAM_ID='K01')
WHERE "POSITION"='GK';

--PLAYER 테이블에서 평균키보다 키가 작은 선수 검색
SELECT * FROM PLAYER WHERE HEIGHT<
	(SELECT AVG(HEIGHT) FROM PLAYER);
	
--PLAYER 테이블에서 전체 키 평균과 포지션별 평균키 구하기
SELECT "POSITION" 포지션, AVG(HEIGHT) "포지션별 평균키",
	(SELECT AVG(HEIGHT) FROM PLAYER) "전체 평균키"
FROM PLAYER WHERE "POSITION" IS NOT NULL GROUP BY "POSITION";

--PLAYER 테이블에서 NICKNAME이 NULL인 선수들은 정태민 선수의 닉네임으로 바꾸기
SELECT NICKNAME FROM PLAYER WHERE PLAYER_NAME='정태민';

UPDATE PLAYER
SET NICKNAME=(SELECT NICKNAME FROM PLAYER WHERE PLAYER_NAME='정태민')
WHERE NICKNAME IS NULL;

--EMPLOYEES 테이블에서 평균급여보다 낮은 사람들의 급여를 10% 인상하기
UPDATE EMPLOYEES
SET SALARY=SALARY*1.1
WHERE SALARY<(SELECT AVG(SALARY) FROM EMPLOYEES);

--PLAYER 테이블에서 평균키보다 큰 선수들 삭제
DELETE FROM PLAYER
WHERE HEIGHT>(SELECT AVG(HEIGHT) FROM PLAYER);

--정남일 선수가 속한 팀의 선수들 검색(PLAYER)
SELECT TEAM_ID FROM PLAYER WHERE PLAYER_NAME='정남일';
SELECT * FROM PLAYER
WHERE TEAM_ID=
	(SELECT TEAM_ID FROM PLAYER WHERE PLAYER_NAME='정남일');

--축구선수들 중에서 키는 평균보다 작고, 몸무게는 평균보다 높은 사람 검색
SELECT * FROM PLAYER WHERE HEIGHT<(SELECT AVG(HEIGHT) FROM PLAYER)
	AND WEIGHT>(SELECT AVG(WEIGHT) FROM PLAYER);

SELECT * FROM
	(SELECT * FROM PLAYER WHERE WEIGHT>(SELECT AVG(WEIGHT) FROM PLAYER))
WHERE HEIGHT <(SELECT AVG(HEIGHT) FROM PLAYER);

--EMP 테이블에서 이름에 L이 있는 사원들의 DNAME과 LOC 검색
SELECT * FROM DEPT d
	JOIN EMP e ON e.DEPTNO=d.DEPTNO
WHERE e.ENAME LIKE '%L%';

(SELECT DEPTNO FROM EMP WHERE ENAME LIKE '%L%');

SELECT * FROM dept;

SELECT DNAME,LOC FROM DEPT 
WHERE DEPTNO IN (SELECT DEPTNO FROM EMP WHERE ENAME LIKE '%L%');--30,30,10,10

SELECT a.*, DNAME, LOC FROM DEPT d
	JOIN (SELECT * FROM EMP WHERE UPPER(ENAME) LIKE '%L%') a
	ON d.DEPTNO=a.DEPTNO;

---------------------------------------------------------
--이윤나 학생이 속한 과 조교수의 급여보다 급여를 더 받는 교수들 검색
SELECT DEPTNO1 FROM T_STUDENT WHERE NAME='이윤나';

SELECT * FROM T_PROFESSOR WHERE DEPTNO=
	(SELECT DEPTNO1 FROM T_STUDENT WHERE NAME='이윤나')
AND "POSITION"='조교수';

SELECT * FROM T_PROFESSOR WHERE
PAY>
	(SELECT PAY FROM T_PROFESSOR WHERE DEPTNO=
		(SELECT DEPTNO1 FROM T_STUDENT WHERE NAME='이윤나')
	AND "POSITION"='조교수');

--각 팀별로 김장관 선수보다 늦게 태어난 선수의 수 검색
SELECT TEAM_ID, COUNT(*) FROM PLAYER WHERE
BIRTH_DATE>
	(SELECT BIRTH_DATE FROM PLAYER WHERE PLAYER_NAME='김장관')
GROUP BY TEAM_ID;

--JOB TITLE중 Manager라는 문자열이 포함된 직원들의 평균 연봉을 JOB TITLE 별로 검색(어려움)
SELECT * FROM JOBS;
SELECT JOB_ID FROM JOBS WHERE JOB_TITLE LIKE '%Manager%';
SELECT * FROM EMPLOYEES;

SELECT j.JOB_TITLE, AVG(e.SALARY) FROM EMPLOYEES e, JOBS j
WHERE e.JOB_ID IN (SELECT JOB_ID FROM JOBS WHERE JOB_TITLE LIKE '%Manager%')
AND e.JOB_ID=j.JOB_ID GROUP BY j.JOB_TITLE;

SELECT j.JOB_TITLE, AVG(e.SALARY) FROM EMPLOYEES e JOIN
	JOBS j ON e.JOB_ID=j.JOB_ID
WHERE e.JOB_ID IN (SELECT JOB_ID FROM JOBS WHERE JOB_TITLE LIKE '%Manager%')
GROUP BY j.JOB_TITLE;

--축구선수들 중에서 각 팀별로 키가 가장 큰 선수들 검색(선수들의 정보까지 전부 나오게 검색)(어려움)
(SELECT MAX(HEIGHT) FROM PLAYER GROUP BY TEAM_ID);

SELECT TEAM_ID,MAX(HEIGHT) FROM PLAYER GROUP BY TEAM_ID ORDER BY 1;

SELECT * FROM PLAYER p JOIN
	(SELECT TEAM_ID, MAX(HEIGHT) MHEIGHT FROM PLAYER GROUP BY TEAM_ID) a
	ON p.TEAM_ID=a.TEAM_ID AND p.HEIGHT=a.MHEIGHT
ORDER BY p.TEAM_ID;

--경기장 중 경기 일정이 20120501~20120503 사이에 있는 경기장 검색
SELECT * FROM SCHEDULE;
SELECT * FROM STADIUM;

SELECT * FROM STADIUM WHERE
STADIUM_ID IN
	(SELECT STADIUM_ID FROM SCHEDULE WHERE SCHE_DATE
	BETWEEN '20120501' AND '20120503');

--PLAYER 테이블에서 생일이 NULL인 선수들은 정준 선수의 생일로 바꾸기
--PLAYER 테이블에서 왕선재 선수와 생일이 같은 선수들 검색하기 팀 아이디로 정렬하기
--EMPLOYEES 테이블에서 Den의 전화번호와 앞 3자리가 같은 사원들 검색
--STADIUM 테이블에서 경기장 이름과 홈팀명, 좌석수, 경기장평균좌석 검색
--PLAYER 테이블에서 정현수 선수가 속한 팀의 선수들 검색

(SELECT TEAM_ID FROM PLAYER WHERE PLAYER_NAME='정현수');
--WHERE절의 등호나 부등호 오른쪽에는 값이 한개만 와야함, 따라서 등호가 아닌 IN으로 비교를 해주어야 한다.
SELECT * FROM PLAYER WHERE TEAM_ID IN
	(SELECT TEAM_ID FROM PLAYER WHERE PLAYER_NAME='정현수');
--T_PROFESSOR 테이블에서 김영조 교수와, 김영조 교수보다 입사일이 늦지만 급여가 높은 교수들의
--PROFNO, NAME, PAY 검색

SELECT * FROM PLAYER;

SELECT * FROM PLAYER WHERE TEAM_ID='K02' OR "POSITION"='GK'
ORDER BY TEAM_ID,PLAYER_NAME;

--UNION
--두 쿼리문의 결과를 합칠 때 사용
--두 쿼리문의 컬럼 갯수가 동일해야 한다.
--중복된 데이터들은 배제한 채 나옴
(SELECT * FROM PLAYER WHERE TEAM_ID='K02')
UNION
(SELECT * FROM PLAYER WHERE "POSITION"='GK') ORDER BY 3,2;

--UNION ALL
--중복된 데이터들을 배제하지 않고 모두 나타낼 때
(SELECT * FROM PLAYER WHERE TEAM_ID='K02')
UNION ALL
(SELECT * FROM PLAYER WHERE "POSITION"='GK') ORDER BY 3,2;

SELECT PLAYER_NAME, HEIGHT FROM PLAYER WHERE TEAM_ID='K02';
CREATE VIEW TESTVIEW(NAME,HEIGHT) AS
	(SELECT PLAYER_NAME, HEIGHT FROM PLAYER WHERE TEAM_ID='K02');





