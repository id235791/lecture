--PLAYER 테이블에서 팀별 최대 몸무게인 선수의 
--TEAM_ID, REGION_NAME, PLAYER_NAME, WEIGTH 검색(WHERE IN 사용하기)
SELECT T.TEAM_ID, T.REGION_NAME, P.PLAYER_NAME, P.WEIGHT
FROM TEAM T, PLAYER P
WHERE P.TEAM_ID = T.TEAM_ID AND
(P.TEAM_ID, P.WEIGHT) IN 
(SELECT TEAM_ID, MAX(WEIGHT) FROM PLAYER GROUP BY TEAM_ID);

--DEPT 테이블의 LOC별 총 급여와 평균 급여를 반올림한 값과
--전체 사원의 평균 급여 검색
SELECT D.LOC, SUM(SAL) "총 급여", ROUND(AVG(SAL)) "평균 급여",
(SELECT ROUND(AVG(SAL)) FROM EMP) "전체 평균 급여"
FROM EMP E JOIN DEPT D
ON E.DEPTNO = D.DEPTNO
GROUP BY D.LOC;

--EMP 테이블에서 HIREDATE가 FORD의 입사년도와 같은 사원 전체 정보 검색
SELECT * FROM EMP E
WHERE TO_CHAR(HIREDATE, 'YYYY-MM-DD')
LIKE(
(SELECT SUBSTR(TO_CHAR(HIREDATE, 'YYYY-MM-DD'), 1, 4)
FROM EMP WHERE ENAME = 'FORD')||'%');

--SELECT TO_CHAR(HIREDATE) FROM EMP WHERE ENAME = 'FORD';

--t_emp2에서 직급이 과장인 사람의 최소연봉자보다 급여를 많이 받는 직원의
--이름, 직급, 급여를 검색
SELECT name 이름, post 직급, to_char(pay,'999,999,999')||'원' "연봉"
FROM t_emp2
WHERE pay>ANY(SELECT pay FROM t_emp2 WHERE post='과장');

--t_student 테이블에서 4학년중 최소 몸무게보다 몸무게가 덜 나가는 학생의
--이름, 학년, 몸무게 검색
SELECT name 이름, grade 학년, weight 몸무게
FROM t_student
WHERE weight<ALL(SELECT weight FROM t_student WHERE grade=4);

--각 학과별로 입사일이 가장 오래된 교수의 교수번호와 이름,
--학과명을 검색(학과이름 순으로 오름차순 정렬)
--SELECT deptno, min(hiredate) FROM T_PROFESSOR GROUP BY deptno;
SELECT p.PROFNO 교수번호, p.name 교수명, to_char(p.HIREDATE,'yyyy-mm-dd') 입사일, d.DNAME 학과명
FROM T_PROFESSOR p, T_DEPARTMENT d
WHERE
	p.DEPTNO=d.DEPTNO
	AND
	(p.deptno,p.hiredate) IN (SELECT deptno, min(hiredate) FROM T_PROFESSOR GROUP BY deptno)
ORDER BY d.dname ASC
;
--자신의 직급의 평균급여보다 같거나 많이 받는 직원의 이름, 직급, 급여 검색
SELECT
	a.name 사원이름,
	nvl(a.post,' ') 직급,
	a.pay 급여
FROM t_emp2 a
WHERE a.pay>=(SELECT avg(b.PAY)
			FROM t_emp2 b WHERE nvl(a.post,' ')=nvl(b.post,' '))
;
SELECT post, avg(pay) avg_pay FROM t_emp2 GROUP BY post;
SELECT
	a.name 사원이름,
	nvl(a.post,' ') 직급,
	a.pay 급여
FROM t_emp2 a, (SELECT nvl(post,' ') post, avg(pay) avg_pay FROM t_emp2 GROUP BY nvl(post,' ')) b
WHERE nvl(a.post,' ')=nvl(b.post,' ') AND a.pay>=b.avg_pay
;
--INSERT 여러개 하기
CREATE TABLE test_emp_a(
	emp_id		NUMBER,
	emp_name	varchar2(100)
);

CREATE TABLE test_emp_b(
	emp_id		NUMBER,
	emp_name	varchar2(100)
);
INSERT INTO test_emp_a values(101, '아이언맨');
INSERT INTO test_emp_b values(201, '캡틴');

--동시에 여러개 테이블에 insert 하기
INSERT ALL
	INTO test_emp_a VALUES(102, '블랙위도우')
	INTO test_emp_b VALUES(202, '비전')
SELECT * FROM DUAL;

SELECT * FROM test_emp_a;
SELECT * FROM test_emp_b;

INSERT INTO test_emp_a
	(SELECT 400,'이순신' FROM dual);

--테이블 내용 두배 만들기
INSERT INTO test_emp_a
	(SELECT * FROM test_emp_a);

INSERT INTO test_emp_b(emp_id)
	(SELECT emp_id FROM test_emp_a); -- 다른테이블의 내용 가져와서 INSERT

INSERT INTO test_emp_a
	(SELECT * FROM TEST_EMP_A
	UNION ALL
	SELECT * FROM test_emp_b);

----------------테이블 스페이스-------------------
SELECT FILE_NAME, TABLESPACE_NAME, AUTOEXTENSIBLE FROM DBA_DATA_FILES;
CREATE TABLESPACE DBMS DATAFILE'C:\oraclexe\app\oracle\oradata\XE\DBMS.DBF'SIZE 200M AUTOEXTEND ON NEXT 5M MAXSIZE 300M;
