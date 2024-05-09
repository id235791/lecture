--���ڿ� ����, �ڸ���, ����
--CONCAT(), SUBSTR(���۹�ȣ, ����), LENGTH()
SELECT CONCAT('FLO', 'WER'), SUBSTR('FLOWER', 3),
	SUBSTR('FLOWER', 1, 2), LENGTH('FLOWER')
FROM DUAL;
-- ���ڿ� || ���ڿ� �� ����
-- ���ڿ� �ڸ��� �÷��� ��� ����� �� ����

--BUSAPPLEFLOWER���� BUS, APPLE, FLOWER �ڸ���
SELECT SUBSTR('BUSAPPLEFLOWER', 1, 3),
	SUBSTR('BUSAPPLEFLOWER', 4, 5),
	SUBSTR('BUSAPPLEFLOWER', 9)
FROM DUAL;

��,�ҹ��ڷ� ��ȯ
SELECT UPPER('kOrEaIt'),LOWER('KoReAiT') FROM DUAL;
--ASCII�ڵ�, CHR
SELECT ASCII('A'), CHR(65)
FROM DUAL;

--������ �Լ�(���迡 99% ����) FROM DUAL
--���밪
SELECT ABS(-10) FROM DUAL;

--��������(1), ��(0), ��������(-1)
SELECT SIGN(-234), SIGN(0), SIGN(123) FROM DUAL;

--������(% ��ⷯ��)
SELECT MOD(10, 8) FROM DUAL;

--������ ū �ֱ��� ����
SELECT CEIL(3.14), CEIL(-3.14) FROM DUAL;

--������ ���� �ֱ��� ����
SELECT FLOOR(3.14), FLOOR(-3.14) FROM DUAL;

--�ݿø�
SELECT ROUND(3.5515616) FROM DUAL;
SELECT ROUND(3.4515616) FROM DUAL;

--����
SELECT TRUNC(3.9) FROM DUAL;

--ROUND(��, �ڸ���)
--�����Ը� �Ҽ��� ù°�ڸ����� �ݿø�
SELECT ROUND(WEIGHT, 2) FROM PLAYER;
SELECT ROUND(10.256, 2) FROM DUAL;

--Ű�� �Ҽ��� ��°�ڸ����� �ݿø�, �����Դ� �Ҽ��� ����
SELECT ROUND(HEIGHT, 2) ||'cm' AS Ű, TRUNC(WEIGHT, 0)||'kg' AS ������ FROM PLAYER;

--PLAYER ���̺��� NATION�� NULL�� �ƴϸ� ���
--NULL�̸� �̵������ ���� �� �����̸�, ���� �˻��ϱ�
--CASE������ ���
--CASE WHEN THEN ELSE END
SELECT PLAYER_NAME, NATION, 
	CASE
		WHEN NATION IS NULL THEN '�̵��'
		ELSE '���'
	END AS "���� ��� ����"
FROM PLAYER;

--COALESCE(�ھ󷹽�) : ��ġ��, �����ϴ�
--NULL�� �ƴϸ� 1�� ����, NULL�̸� 2�� ����, �� �� NULL�̸� NULL
SELECT PLAYER_NAME, NICKNAME,
	COALESCE(NICKNAME, PLAYER_NAME) AS KNOWN
FROM PLAYER;

--PLAYER ���̺��� NICKNAME�� ������ '���� ����'���� ���� �� �˻�
SELECT PLAYER_NAME, NICKNAME,
	COALESCE(NICKNAME, '���� ����') AS ����
FROM PLAYER;

TO_NUMBER()
SELECT TO_NUMBER(SYSDATE-TO_DATE('2000-01-01 00:00:00','YYYY-MM-DD HH24:MI:SS'))/365 FROM dual;
TO_DATE()
TO_CHAR()
SELECT TO_CHAR(TO_NUMBER('30.2')) FROM DUAL;
SELECT TO_CHAR(TO_DATE('2020/01/01','YYYY/MM/DD')) FROM dual;

--���� �Լ�(NULL�� �������� �ʴ´�.)
--WHERE�������� ����� �� ����.
--�������� ���� �ϳ��� ������ ��Ÿ��
--AVG() ���
--MAX() �ִ�
--MIN() �ּڰ�
--SUM()	����
--COUNT() ����
SELECT AVG(HEIGHT), MAX(HEIGHT), MIN(HEIGHT), SUM(HEIGHT) FROM PLAYER;

SELECT * FROM PLAYER;

SELECT COUNT(HEIGHT) FROM PLAYER;

--PLAYER ���̺��� HEIGTH ���� �˻�(NULL�����ؼ� COUNT�ϱ�)
SELECT COUNT(NVL(HEIGHT, 0)) FROM PLAYER;
-----------------------------------------------------------------------------
--ORDER BY : ����
--ASC : ��������(���� ����)
--DESC : ��������

--PLAYER ���̺��� Ű ������ �˻�(��������)
SELECT PLAYER_NAME, HEIGHT 
FROM PLAYER
WHERE HEIGHT IS NOT NULL
ORDER BY HEIGHT ASC;

--PLAYER ���̺��� Ű ��, ������ ��(��������)���� �˻�
--NULL�� �ƴ� ���� �˻�
--ù��° �÷���(Ʃ��)�� ������ �ι�° ������ �Ѵ�.
SELECT PLAYER_NAME, HEIGHT, WEIGHT 
FROM PLAYER
WHERE HEIGHT IS NOT NULL AND WEIGHT IS NOT NULL
ORDER BY HEIGHT, WEIGHT;

--GROUP BY : ~��(�� : ������ �� ���Ű)

--SELECT ??? FROM ???
--GROUP BY �÷���
--HAVING ���ǽ�

--PLAYER ���̺��� ������ �˻�
SELECT "POSITION"
FROM PLAYER
GROUP BY "POSITION"
HAVING "POSITION" IS NOT NULL;

--WHERE������ ������ ó���� �� �ִٸ� �ݵ�� WHERE������ ���� ó�����ش�.
--HAVING������ �� ȿ�����̴�.
SELECT "POSITION"
FROM PLAYER
WHERE "POSITION" IS NOT NULL
GROUP BY "POSITION";

--t_professor ���̺��� �а����� ������ ��� ���ʽ� ���
SELECT deptno, AVG(bonus) FROM t_professor GROUP BY deptno;--NULL ����
SELECT deptno, ROUND(AVG(nvl(bonus,0)),1) FROM t_professor GROUP BY deptno;

-------------------------�ǽ�------------------------------
--EMPLOYEES ���̺��� JOB_ID���� ��� SALARY�� 10000�̸���
--(SALARY �հ�, ���, �ִ�, �ּڰ�, JOB_ID�� ���) �˻��ϱ�
--JOB_ID ���ĺ� ������ ����(��������)
SELECT JOB_ID , SUM(SALARY), AVG(SALARY),
	MAX(SALARY), MIN(SALARY), COUNT(JOB_ID)
FROM EMPLOYEES
GROUP BY JOB_ID
HAVING AVG(SALARY) < 10000
ORDER BY JOB_ID;

--�μ��� ��ձ޿��� ����ϵ�, ��ձ޿��� 450 ���� ���� �μ��� ���
SELECT deptno, AVG(pay) FROM T_PROFESSOR GROUP BY deptno HAVING AVG(nvl(pay,0))>450;

--PLAYER ���̺��� �����԰� 80�̻��� �������� ���Ű�� 180�̻��� ������ �˻�
SELECT "POSITION", AVG(HEIGHT), MIN(WEIGHT)
FROM PLAYER
WHERE WEIGHT >= 80
GROUP BY "POSITION"
HAVING AVG(HEIGHT) >= 180;

--t_professor ���̺��� �а���(deptno) �׸��� ���޺�(position)�� �������� ��� �޿��� ���
SELECT deptno, position, AVG(pay) FROM t_professor
GROUP BY deptno,position ORDER BY deptno ASC;

--t_emp ���̺��� �Ŵ�����(MGR)�� �����ϴ� �������� ���Ŵ�����, ������������
--���޿��Ѿס��� ���޿���ա��� ������� (COMM) ��ա� ���޾� �� ���
-- �� ������� (job = president)���� 
SELECT MGR,COUNT(*),SUM(nvl(SAL,0)),AVG(nvl(SAL,0)),AVG(nvl(COMM,0))
FROM T_EMP WHERE MGR IS NOT NULL GROUP BY MGR;

--t_professor ���̺��� ������ ������ Ȥ�� ������ �� ��� �߿�
--����(deptno)�� ����ȣ, �Ҽӱ��� �Ѽ�, �ټ��� ���, �޿����, ���ʽ� ����� ���
SELECT deptno, count(*), AVG(SYSDATE-hiredate), avg(pay), avg(COALESCE(bonus,0))
FROM T_PROFESSOR WHERE POSITION IN ('������','������') GROUP BY deptno;

--t_student ���̺��� �а���(deptno1)��,
--�а���ȣ, �ִ������ - �ּҸ����� ���� ���� ���
SELECT deptno1,MAX(weight)-MIN(weight) AS �ִ��ּҸ�������
FROM T_STUDENT GROUP BY deptno1 HAVING MAX(weight)-MIN(weight)>=30;
















