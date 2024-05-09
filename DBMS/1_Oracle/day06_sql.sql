--JOIN
--EMP 테이블 사원번호로 DEPT 테이블의 지역 검색하기
SELECT E.DEPTNO, E.ENAME, D.LOC
FROM EMP E JOIN DEPT D
ON E.DEPTNO = D.DEPTNO;

--PLAYER 테이블에서 송종국 선수가 속한 팀의 전화번호 검색하기
SELECT P.TEAM_ID, P.PLAYER_NAME, T.TEL
FROM PLAYER P JOIN TEAM T
ON P.TEAM_ID = T.TEAM_ID AND PLAYER_NAME = '송종국';

SELECT P.TEAM_ID, P.PLAYER_NAME, T.TEL
FROM PLAYER P JOIN TEAM T
ON P.TEAM_ID = T.TEAM_ID
WHERE PLAYER_NAME = '송종국';

--JOBS 테이블에서 JOB_ID로 EMPLOYEES의 EMAIL, 성 이름 검색
--CONCATENATION 사용
SELECT J.JOB_ID, J.JOB_TITLE, 
	E.EMAIL, E.LAST_NAME||' '||E.FIRST_NAME AS 이름
FROM JOBS J JOIN EMPLOYEES E
ON J.JOB_ID = E.JOB_ID;

--t_student 테이블과 t_department 테이블을 사용하여 학생이름,
--1전공 학과번호, 1전공 학과 이름을 출력
SELECT s.name 학생이름, s.deptno1 "학과번호", d.dname "학과이름"
FROM t_student s, t_department d WHERE s.deptno1 = d.deptno;

--t_student 테이블, t_professor 테이블을 join하여 ‘학생이름’, ‘지도교수 번호’,
--‘지도교수이름’ 을 출력하여 문제점 확인
SELECT s.NAME, s.PROFNO, p.name FROM T_STUDENT s, T_PROFESSOR p
WHERE s.PROFNO=p.PROFNO;	--OUTER JOIN 사용

--t_student, t_department, t_professor 테이블 을 join 하여 학생의 이름,
--학과이름, 지도교수 이름 을 출력
SELECT
	s.name 학생이름,
	d.DNAME 학과이름,
	p.name 교수이름
FROM T_STUDENT s
	JOIN T_DEPARTMENT d ON s.DEPTNO1=d.DEPTNO
	JOIN T_PROFESSOR p ON s.PROFNO=p.PROFNO;

--비등가 조인
--급여로 등급 나누기
SELECT * FROM SALGRADE;

SELECT E.EMPNO, D.DNAME, E.SAL, S.GRADE 
FROM EMP E JOIN SALGRADE S
ON E.SAL BETWEEN S.LOSAL AND S.HISAL
JOIN DEPT D 
ON E.DEPTNO = D.DEPTNO;

SELECT E.EMPNO, D.DNAME, E.SAL, S.GRADE
FROM EMP E, SALGRADE S, DEPT D
WHERE E.SAL BETWEEN S.LOSAL AND S.HISAL
AND E.DEPTNO = D.DEPTNO;

--EMPLOYEES 테이블에서 HIREDATE가
--2003년~2005년까지인 사원 검색
--HIRE_DATE, FIRST_NAME, DEPARTMENT_NAME
--HIRE_DATE순으로 정렬
--HTML파일로 열어놓기
SELECT TO_CHAR(E.HIRE_DATE, 'YYYY-MM') "입사 날짜",
E.FIRST_NAME 이름, D.DEPARTMENT_NAME 부서명
FROM EMPLOYEES E
JOIN DEPARTMENTS D
ON E.DEPARTMENT_ID = D.DEPARTMENT_ID AND
E.HIRE_DATE BETWEEN TO_DATE('2003-01', 'YYYY-MM') AND TO_DATE('2006-01', 'YYYY-MM')
ORDER BY E.HIRE_DATE;

--네츄럴 조인(NATURAL JOIN)
SELECT *
	FROM EMP E INNER JOIN DEPT D
		ON E.DEPTNO = D.DEPTNO;

SELECT *
	FROM EMP E JOIN DEPT D
		USING(DEPTNO);
	
SELECT *
	FROM EMP NATURAL JOIN DEPT;

--외부 조인(OUTER JOIN)
--LEFT OUTER JOIN
SELECT S.STADIUM_NAME, S.STADIUM_ID,
	S.SEAT_COUNT, S.HOMETEAM_ID
FROM STADIUM S LEFT OUTER JOIN TEAM T
ON S.HOMETEAM_ID = T.TEAM_ID AND HOMETEAM_ID IS NOT NULL;

SELECT S.STADIUM_NAME, S.STADIUM_ID,
	S.SEAT_COUNT, S.HOMETEAM_ID
FROM STADIUM S LEFT OUTER JOIN TEAM T
ON S.HOMETEAM_ID = T.TEAM_ID
WHERE HOMETEAM_ID IS NOT NULL;

--RIGHT OUTER JOIN
SELECT S.STADIUM_NAME, S.STADIUM_ID,
	S.SEAT_COUNT, S.HOMETEAM_ID 
FROM STADIUM S RIGHT OUTER JOIN TEAM T
ON S.HOMETEAM_ID = T.TEAM_ID;

--FULL OUTER JOIN
SELECT S.STADIUM_NAME, S.STADIUM_ID,
	S.SEAT_COUNT, S.HOMETEAM_ID 
FROM STADIUM S FULL OUTER JOIN TEAM T
ON S.HOMETEAM_ID = T.TEAM_ID;

--크로스 조인(CROSS JOIN)
--거의 쓰지 않음. 이론상 존재
SELECT * 
FROM EMPLOYEES E CROSS JOIN DEPARTMENTS D;
--목적 : 모든 경우를 고려한다.

--SELF 조인
SELECT E1.ENAME 사원, E2.ENAME 매니저 FROM EMP E1 JOIN EMP E2
ON E1.MGR = E2.EMPNO;

--T_DEPT2 테이블에서 부서명과 그 부서의 상위부서를 출력
SELECT
	d1.DNAME 부서명,
	d2.dname 상위부서명
FROM T_DEPT2 d1,T_DEPT2 d2
WHERE d1.PDEPT=d2.DCODE;

--T_PROFESSOR 테이블에서 교수번호, 교수이름, 입사일, 자신보다 입사일이 빠른 사람의 인원수를
--인원수 오름차순으로 출력
SELECT
	p1.PROFNO 교수번호,
	p1.NAME 교수명,
	to_char(p1.HIREDATE,'yyyy-mm-dd') 입사일,
	count(p2.HIREDATE) 빠른사람
FROM T_PROFESSOR p1
	LEFT OUTER JOIN T_PROFESSOR p2 ON p1.HIREDATE>p2.HIREDATE
GROUP BY p1.PROFNO, p1.NAME, p1.HIREDATE
ORDER BY 빠른사람;
--------------------------------------------------------------------------------------
--t_emp2, t_post / 사원의 이름과 직급, 현재연봉, 해당직급의 연봉의 하한금액(s_pay)과 상한금액(e_pay)
SELECT 
	e.NAME,
	e.POST,
	e.PAY,
	p.S_PAY,
	p.E_PAY
FROM T_EMP2 e
	JOIN T_POST p ON e.POST = p.POST;

--t_customer, t_gift / 마일리지 포인트별로 받을수 있는 상품을 조회하여 고객 이름, 포인트, 상품명 검색
SELECT 
	c.C_NAME,
	c.C_POINT,
	g.G_NAME
FROM T_CUSTOMER c
	JOIN T_GIFT g ON c.C_POINT BETWEEN g.G_START AND g.G_END;

--앞 예제에서 조회한 상품의 이름(g_name)과 필요수량이 몇개인지 조회
SELECT 
	g.G_NAME,
	COUNT(*)
FROM T_CUSTOMER c
	JOIN T_GIFT g ON c.C_POINT BETWEEN g.G_START AND g.G_END
GROUP BY g.G_NAME;

--t_student, t_exam01, t_credit / 학생들의 이름, 점수, 학점
SELECT
	s.NAME,
	e.TOTAL,
	c.GRADE
FROM T_STUDENT s
	JOIN T_EXAM01 e ON s.STUDNO=e.STUDNO
	JOIN T_CREDIT c ON e.TOTAL BETWEEN c.MIN_POINT AND c.MAX_POINT;

--t_customer, t_gift / 자기 포인트보다 낮은 포인트의 상품 중 한가지를 선택할수 있다고 할때
--'산악용자전거'를 선택할 수 있는 고객명과 포인트, 상품명 검색
SELECT c.C_NAME,c.C_POINT,g.G_NAME FROM T_CUSTOMER c
	JOIN T_GIFT g ON c.C_POINT>g.G_START AND g.G_NAME = '산악용자전거';

-- t_emp2, t_post / 사원들의 이름,나이,현재직급,‘예상직급’ 검색
--‘예상직급’은 나이로 계산하며 해당 나이가 받아야 하는 직급을 의미 / 나이순 정렬
SELECT e.NAME, TO_CHAR(SYSDATE,'yyyy')-TO_CHAR(e.BIRTHDAY,'yyyy')+1 "나이", NVL(e.POST,' ') 현재직급, p.POST
FROM T_EMP2 e JOIN T_POST p ON TO_CHAR(SYSDATE,'yyyy')-TO_CHAR(e.BIRTHDAY,'yyyy')+1 BETWEEN p.S_AGE AND p.E_AGE
ORDER BY 2;












