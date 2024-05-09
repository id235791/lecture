SELECT PLAYER_ID AS �������̵�, PLAYER_NAME AS �����̸� FROM PLAYER;
--AS Ű����� ������ �����ϴ�
SELECT PLAYER_ID �������̵�, PLAYER_NAME �����̸� FROM PLAYER;

SELECT PLAYER.PLAYER_NAME, TEAM.ADDRESS FROM PLAYER, TEAM;

SELECT P.PLAYER_NAME, T.ADDRESS FROM PLAYER P, TEAM T;

SELECT * FROM player;
SELECT * FROM TEAM;
SELECT * FROM STADIUM;
--PLAYER ���̺��� BACK_NO�� ���ȣ, NICKNAME�� ������������ �˻�
SELECT BACK_NO ���ȣ, NICKNAME �������� FROM PLAYER;
--STADIUM ���̺��� ADDRESS, TEAM���̺��� TEL �˻�
SELECT S.ADDRESS,T.TEL FROM STADIUM S,TEAM T;
--PLAYER ���̺��� TEAM_ID�� �Ҽ����ڵ�, TEAM ���̺��� TEAM_NAME�� ���̸�����,
--STADIUM ���̺��� ADDRESS�� �ּҷ� �˻�
SELECT P.TEAM_ID, T.TEAM_NAME, S.ADDRESS FROM PLAYER P,TEAM T,STADIUM S;
--STADIUM ���̺��� ADDRESS�� ����� �ּ� �� �˻�
--���Ⱑ ���Ե� ������ "" �� �����ش�.
SELECT S.ADDRESS "����� �ּ�" FROM STADIUM S;

SELECT PLAYER_NAME|| ' ' || NICKNAME "�����̸� ����" FROM PLAYER;

SELECT * FROM PLAYER
WHERE BIRTH_DATE>=TO_DATE('1987-01-01','YYYY-MM-DD')
AND BIRTH_DATE<=TO_DATE('1989-12-31 23:59:59','YYYY-MM-DD HH24:MI:SS');

SELECT * FROM PLAYER
WHERE BIRTH_DATE BETWEEN TO_DATE('1987-01-01','YYYY-MM-DD')
AND TO_DATE('1989-12-31 23:59:59','YYYY-MM-DD HH24:MI:SS');

SELECT * FROM PLAYER WHERE PLAYER_NAME LIKE('��%');
--SELECT * FROM USER WHERE JUMIN LIKE '______1%'
--���� ���� ����ϸ� ���� �÷� ���� ������ ����������.

--PLAYER ���̺��� �达�� �̾��� ���� �˻�
SELECT * FROM PLAYER WHERE PLAYER_NAME LIKE('��%') OR PLAYER_NAME LIKE('��%');

--STADIUM �߿� ��⵵�� �ִ� ����� �˻�
SELECT * FROM STADIUM WHERE ADDRESS LIKE ('��⵵%');

--PLAYER ���̺��� �̸��� ���� ���� ���� �˻�
SELECT * FROM PLAYER WHERE PLAYER_NAME LIKE('%��%');

SELECT ENAME �����, SAL �޿�,
	CASE
		WHEN SAL>=3000 THEN 'HIGH'
		WHEN SAL>=1000 THEN 'MID'
		ELSE 'LOW'
	END AS Ŭ����
FROM EMP;

SELECT ENAME �����, SAL �޿�,
	CASE
		WHEN SAL>=2000 THEN 1000
		ELSE(
			CASE
				WHEN SAL>=1000 THEN 500
				ELSE 0
			END
		)		
	END ���ʽ�
FROM EMP;

--STADIUM ���̺��� ������� �ּҿ� ����� �̸� �¼� ��
--�¼��� 0�̻� 30000 ���ϸ� s 30001 �̻� 50000 ���ϸ� 'M'
--50001�̻��̸� 'L'�� �˻�

SELECT ADDRESS �ּ�,STADIUM_NAME �̸�, SEAT_COUNT �¼���,
CASE
	WHEN SEAT_COUNT BETWEEN 0 AND 30000 THEN 'S'
	WHEN SEAT_COUNT BETWEEN 30001 AND 50000 THEN 'M'
	ELSE 'L'
	END "ũ��"
FROM STADIUM;

--PLAYER ���̺��� ���� �̸�, Ű, ������
--WEIGHT�� 50~70�̸� 'L' 71~80�̸� 'M' NULL���̸� '�̵��' �� �ƴϸ� 'H'
SELECT PLAYER_NAME "���� �̸�", HEIGHT Ű, WEIGHT ������,
CASE
	WHEN WEIGHT>=50 AND WEIGHT<=70 THEN 'L'
	WHEN WEIGHT>70 AND WEIGHT<=80 THEN 'M'
	WHEN WEIGHT IS NULL THEN '�̵��'
	ELSE 'H'
END "��ũ��"
FROM PLAYER;

--NULL ó���ϱ�
--NVL(�÷���,��ü�Ұ�) : �����Ͱ� NULL���� ��� ���� ��ü�� ������ ��� ����Ѵ�.
SELECT PLAYER_NAME, NVL(WEIGHT,0) FROM PLAYER;


--���Ϲ� ���� ��ü(���̺� ���� : 3 / �÷� ���� 3~5)
--��Ϲ�ȣ/����/����/Ÿ��
/*��Ϲ�ȣ 	������ڵ�(FK)		�°����̵�(FK)		���Ϲ� ��ȣ(FK)
 * 1		A01				abcd			1
 * 2		A01				sun10			2
 * 3		F03				gil6			3
 * 4		A02				hangle			4
 * 
 * ���Ϲ� ���̺�
 * ���Ϲ� ��ȣ		����		����		�°� ���̵� 	Ÿ��
 * 1			2		10		abcd		ĳ����
 * 2			12		20		sun10
 * 3			7		20		gil6
 * 4			2		40		hangle
 * 
 * �°� ���̺�
 * ���̵�/�̸�/����/��ȭ��ȣ/
 * abcd		���ټ�	20	01011111111
 * sun10	�̼���	20	01012341234
 * gil6		ȫ�浿	10	01044556677
 * hangle	�������	30	01018754675
 * 
 * ����� ���̺�
 * ������ڵ�/�����/������/�װ���
 * K01	����	����	�ƽþƳ�
 * K02	����	����	�����װ�
 * F03	�ĸ�	����	����������
 */










