--PLAYER ���̺��� ���� �ִ� �������� ������ 
--TEAM_ID, REGION_NAME, PLAYER_NAME, WEIGTH �˻�(WHERE IN ����ϱ�)
SELECT T.TEAM_ID, T.REGION_NAME, P.PLAYER_NAME, P.WEIGHT
FROM TEAM T, PLAYER P
WHERE P.TEAM_ID = T.TEAM_ID AND
(P.TEAM_ID, P.WEIGHT) IN 
(SELECT TEAM_ID, MAX(WEIGHT) FROM PLAYER GROUP BY TEAM_ID);

--DEPT ���̺��� LOC�� �� �޿��� ��� �޿��� �ݿø��� ����
--��ü ����� ��� �޿� �˻�
SELECT D.LOC, SUM(SAL) "�� �޿�", ROUND(AVG(SAL)) "��� �޿�",
(SELECT ROUND(AVG(SAL)) FROM EMP) "��ü ��� �޿�"
FROM EMP E JOIN DEPT D
ON E.DEPTNO = D.DEPTNO
GROUP BY D.LOC;

--EMP ���̺��� HIREDATE�� FORD�� �Ի�⵵�� ���� ��� ��ü ���� �˻�
SELECT * FROM EMP E
WHERE TO_CHAR(HIREDATE, 'YYYY-MM-DD')
LIKE(
(SELECT SUBSTR(TO_CHAR(HIREDATE, 'YYYY-MM-DD'), 1, 4)
FROM EMP WHERE ENAME = 'FORD')||'%');

--SELECT TO_CHAR(HIREDATE) FROM EMP WHERE ENAME = 'FORD';

--t_emp2���� ������ ������ ����� �ּҿ����ں��� �޿��� ���� �޴� ������
--�̸�, ����, �޿��� �˻�
SELECT name �̸�, post ����, to_char(pay,'999,999,999')||'��' "����"
FROM t_emp2
WHERE pay>ANY(SELECT pay FROM t_emp2 WHERE post='����');

--t_student ���̺��� 4�г��� �ּ� �����Ժ��� �����԰� �� ������ �л���
--�̸�, �г�, ������ �˻�
SELECT name �̸�, grade �г�, weight ������
FROM t_student
WHERE weight<ALL(SELECT weight FROM t_student WHERE grade=4);

--�� �а����� �Ի����� ���� ������ ������ ������ȣ�� �̸�,
--�а����� �˻�(�а��̸� ������ �������� ����)
--SELECT deptno, min(hiredate) FROM T_PROFESSOR GROUP BY deptno;
SELECT p.PROFNO ������ȣ, p.name ������, to_char(p.HIREDATE,'yyyy-mm-dd') �Ի���, d.DNAME �а���
FROM T_PROFESSOR p, T_DEPARTMENT d
WHERE
	p.DEPTNO=d.DEPTNO
	AND
	(p.deptno,p.hiredate) IN (SELECT deptno, min(hiredate) FROM T_PROFESSOR GROUP BY deptno)
ORDER BY d.dname ASC
;
--�ڽ��� ������ ��ձ޿����� ���ų� ���� �޴� ������ �̸�, ����, �޿� �˻�
SELECT
	a.name ����̸�,
	nvl(a.post,' ') ����,
	a.pay �޿�
FROM t_emp2 a
WHERE a.pay>=(SELECT avg(b.PAY)
			FROM t_emp2 b WHERE nvl(a.post,' ')=nvl(b.post,' '))
;
SELECT post, avg(pay) avg_pay FROM t_emp2 GROUP BY post;
SELECT
	a.name ����̸�,
	nvl(a.post,' ') ����,
	a.pay �޿�
FROM t_emp2 a, (SELECT nvl(post,' ') post, avg(pay) avg_pay FROM t_emp2 GROUP BY nvl(post,' ')) b
WHERE nvl(a.post,' ')=nvl(b.post,' ') AND a.pay>=b.avg_pay
;
--INSERT ������ �ϱ�
CREATE TABLE test_emp_a(
	emp_id		NUMBER,
	emp_name	varchar2(100)
);

CREATE TABLE test_emp_b(
	emp_id		NUMBER,
	emp_name	varchar2(100)
);
INSERT INTO test_emp_a values(101, '���̾��');
INSERT INTO test_emp_b values(201, 'ĸƾ');

--���ÿ� ������ ���̺� insert �ϱ�
INSERT ALL
	INTO test_emp_a VALUES(102, '��������')
	INTO test_emp_b VALUES(202, '����')
SELECT * FROM DUAL;

SELECT * FROM test_emp_a;
SELECT * FROM test_emp_b;

INSERT INTO test_emp_a
	(SELECT 400,'�̼���' FROM dual);

--���̺� ���� �ι� �����
INSERT INTO test_emp_a
	(SELECT * FROM test_emp_a);

INSERT INTO test_emp_b(emp_id)
	(SELECT emp_id FROM test_emp_a); -- �ٸ����̺��� ���� �����ͼ� INSERT

INSERT INTO test_emp_a
	(SELECT * FROM TEST_EMP_A
	UNION ALL
	SELECT * FROM test_emp_b);

----------------���̺� �����̽�-------------------
SELECT FILE_NAME, TABLESPACE_NAME, AUTOEXTENSIBLE FROM DBA_DATA_FILES;
CREATE TABLESPACE DBMS DATAFILE'C:\oraclexe\app\oracle\oradata\XE\DBMS.DBF'SIZE 200M AUTOEXTEND ON NEXT 5M MAXSIZE 300M;
