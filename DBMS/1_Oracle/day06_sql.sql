--JOIN
--EMP ���̺� �����ȣ�� DEPT ���̺��� ���� �˻��ϱ�
SELECT E.DEPTNO, E.ENAME, D.LOC
FROM EMP E JOIN DEPT D
ON E.DEPTNO = D.DEPTNO;

--PLAYER ���̺��� ������ ������ ���� ���� ��ȭ��ȣ �˻��ϱ�
SELECT P.TEAM_ID, P.PLAYER_NAME, T.TEL
FROM PLAYER P JOIN TEAM T
ON P.TEAM_ID = T.TEAM_ID AND PLAYER_NAME = '������';

SELECT P.TEAM_ID, P.PLAYER_NAME, T.TEL
FROM PLAYER P JOIN TEAM T
ON P.TEAM_ID = T.TEAM_ID
WHERE PLAYER_NAME = '������';

--JOBS ���̺��� JOB_ID�� EMPLOYEES�� EMAIL, �� �̸� �˻�
--CONCATENATION ���
SELECT J.JOB_ID, J.JOB_TITLE, 
	E.EMAIL, E.LAST_NAME||' '||E.FIRST_NAME AS �̸�
FROM JOBS J JOIN EMPLOYEES E
ON J.JOB_ID = E.JOB_ID;

--t_student ���̺�� t_department ���̺��� ����Ͽ� �л��̸�,
--1���� �а���ȣ, 1���� �а� �̸��� ���
SELECT s.name �л��̸�, s.deptno1 "�а���ȣ", d.dname "�а��̸�"
FROM t_student s, t_department d WHERE s.deptno1 = d.deptno;

--t_student ���̺�, t_professor ���̺��� join�Ͽ� ���л��̸���, ���������� ��ȣ��,
--�����������̸��� �� ����Ͽ� ������ Ȯ��
SELECT s.NAME, s.PROFNO, p.name FROM T_STUDENT s, T_PROFESSOR p
WHERE s.PROFNO=p.PROFNO;	--OUTER JOIN ���

--t_student, t_department, t_professor ���̺� �� join �Ͽ� �л��� �̸�,
--�а��̸�, �������� �̸� �� ���
SELECT
	s.name �л��̸�,
	d.DNAME �а��̸�,
	p.name �����̸�
FROM T_STUDENT s
	JOIN T_DEPARTMENT d ON s.DEPTNO1=d.DEPTNO
	JOIN T_PROFESSOR p ON s.PROFNO=p.PROFNO;

--�� ����
--�޿��� ��� ������
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

--EMPLOYEES ���̺��� HIREDATE��
--2003��~2005������� ��� �˻�
--HIRE_DATE, FIRST_NAME, DEPARTMENT_NAME
--HIRE_DATE������ ����
--HTML���Ϸ� �������
SELECT TO_CHAR(E.HIRE_DATE, 'YYYY-MM') "�Ի� ��¥",
E.FIRST_NAME �̸�, D.DEPARTMENT_NAME �μ���
FROM EMPLOYEES E
JOIN DEPARTMENTS D
ON E.DEPARTMENT_ID = D.DEPARTMENT_ID AND
E.HIRE_DATE BETWEEN TO_DATE('2003-01', 'YYYY-MM') AND TO_DATE('2006-01', 'YYYY-MM')
ORDER BY E.HIRE_DATE;

--���� ����(NATURAL JOIN)
SELECT *
	FROM EMP E INNER JOIN DEPT D
		ON E.DEPTNO = D.DEPTNO;

SELECT *
	FROM EMP E JOIN DEPT D
		USING(DEPTNO);
	
SELECT *
	FROM EMP NATURAL JOIN DEPT;

--�ܺ� ����(OUTER JOIN)
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

--ũ�ν� ����(CROSS JOIN)
--���� ���� ����. �̷л� ����
SELECT * 
FROM EMPLOYEES E CROSS JOIN DEPARTMENTS D;
--���� : ��� ��츦 ����Ѵ�.

--SELF ����
SELECT E1.ENAME ���, E2.ENAME �Ŵ��� FROM EMP E1 JOIN EMP E2
ON E1.MGR = E2.EMPNO;

--T_DEPT2 ���̺��� �μ���� �� �μ��� �����μ��� ���
SELECT
	d1.DNAME �μ���,
	d2.dname �����μ���
FROM T_DEPT2 d1,T_DEPT2 d2
WHERE d1.PDEPT=d2.DCODE;

--T_PROFESSOR ���̺��� ������ȣ, �����̸�, �Ի���, �ڽź��� �Ի����� ���� ����� �ο�����
--�ο��� ������������ ���
SELECT
	p1.PROFNO ������ȣ,
	p1.NAME ������,
	to_char(p1.HIREDATE,'yyyy-mm-dd') �Ի���,
	count(p2.HIREDATE) �������
FROM T_PROFESSOR p1
	LEFT OUTER JOIN T_PROFESSOR p2 ON p1.HIREDATE>p2.HIREDATE
GROUP BY p1.PROFNO, p1.NAME, p1.HIREDATE
ORDER BY �������;
--------------------------------------------------------------------------------------
--t_emp2, t_post / ����� �̸��� ����, ���翬��, �ش������� ������ ���ѱݾ�(s_pay)�� ���ѱݾ�(e_pay)
SELECT 
	e.NAME,
	e.POST,
	e.PAY,
	p.S_PAY,
	p.E_PAY
FROM T_EMP2 e
	JOIN T_POST p ON e.POST = p.POST;

--t_customer, t_gift / ���ϸ��� ����Ʈ���� ������ �ִ� ��ǰ�� ��ȸ�Ͽ� �� �̸�, ����Ʈ, ��ǰ�� �˻�
SELECT 
	c.C_NAME,
	c.C_POINT,
	g.G_NAME
FROM T_CUSTOMER c
	JOIN T_GIFT g ON c.C_POINT BETWEEN g.G_START AND g.G_END;

--�� �������� ��ȸ�� ��ǰ�� �̸�(g_name)�� �ʿ������ ����� ��ȸ
SELECT 
	g.G_NAME,
	COUNT(*)
FROM T_CUSTOMER c
	JOIN T_GIFT g ON c.C_POINT BETWEEN g.G_START AND g.G_END
GROUP BY g.G_NAME;

--t_student, t_exam01, t_credit / �л����� �̸�, ����, ����
SELECT
	s.NAME,
	e.TOTAL,
	c.GRADE
FROM T_STUDENT s
	JOIN T_EXAM01 e ON s.STUDNO=e.STUDNO
	JOIN T_CREDIT c ON e.TOTAL BETWEEN c.MIN_POINT AND c.MAX_POINT;

--t_customer, t_gift / �ڱ� ����Ʈ���� ���� ����Ʈ�� ��ǰ �� �Ѱ����� �����Ҽ� �ִٰ� �Ҷ�
--'��ǿ�������'�� ������ �� �ִ� ����� ����Ʈ, ��ǰ�� �˻�
SELECT c.C_NAME,c.C_POINT,g.G_NAME FROM T_CUSTOMER c
	JOIN T_GIFT g ON c.C_POINT>g.G_START AND g.G_NAME = '��ǿ�������';

-- t_emp2, t_post / ������� �̸�,����,��������,���������ޡ� �˻�
--���������ޡ��� ���̷� ����ϸ� �ش� ���̰� �޾ƾ� �ϴ� ������ �ǹ� / ���̼� ����
SELECT e.NAME, TO_CHAR(SYSDATE,'yyyy')-TO_CHAR(e.BIRTHDAY,'yyyy')+1 "����", NVL(e.POST,' ') ��������, p.POST
FROM T_EMP2 e JOIN T_POST p ON TO_CHAR(SYSDATE,'yyyy')-TO_CHAR(e.BIRTHDAY,'yyyy')+1 BETWEEN p.S_AGE AND p.E_AGE
ORDER BY 2;












