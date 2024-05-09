--문자열 붙임, 자르기, 길이
--CONCAT(), SUBSTR(시작번호, 길이), LENGTH()
SELECT CONCAT('FLO', 'WER'), SUBSTR('FLOWER', 3),
	SUBSTR('FLOWER', 1, 2), LENGTH('FLOWER')
FROM DUAL;
-- 문자열 || 문자열 과 동일
-- 문자열 자리에 컬럼을 대신 사용할 수 있음

--BUSAPPLEFLOWER에서 BUS, APPLE, FLOWER 자르기
SELECT SUBSTR('BUSAPPLEFLOWER', 1, 3),
	SUBSTR('BUSAPPLEFLOWER', 4, 5),
	SUBSTR('BUSAPPLEFLOWER', 9)
FROM DUAL;

대,소문자로 변환
SELECT UPPER('kOrEaIt'),LOWER('KoReAiT') FROM DUAL;
--ASCII코드, CHR
SELECT ASCII('A'), CHR(65)
FROM DUAL;

--숫자형 함수(시험에 99% 출제) FROM DUAL
--절대값
SELECT ABS(-10) FROM DUAL;

--양의정수(1), 영(0), 음의정수(-1)
SELECT SIGN(-234), SIGN(0), SIGN(123) FROM DUAL;

--나머지(% 모듈러스)
SELECT MOD(10, 8) FROM DUAL;

--값보다 큰 최근접 정수
SELECT CEIL(3.14), CEIL(-3.14) FROM DUAL;

--값보다 작은 최근접 정수
SELECT FLOOR(3.14), FLOOR(-3.14) FROM DUAL;

--반올림
SELECT ROUND(3.5515616) FROM DUAL;
SELECT ROUND(3.4515616) FROM DUAL;

--버림
SELECT TRUNC(3.9) FROM DUAL;

--ROUND(값, 자릿수)
--몸무게를 소수점 첫째자리까지 반올림
SELECT ROUND(WEIGHT, 2) FROM PLAYER;
SELECT ROUND(10.256, 2) FROM DUAL;

--키는 소수점 둘째자리까지 반올림, 몸무게는 소수점 버림
SELECT ROUND(HEIGHT, 2) ||'cm' AS 키, TRUNC(WEIGHT, 0)||'kg' AS 몸무게 FROM PLAYER;

--PLAYER 테이블에서 NATION이 NULL이 아니면 등록
--NULL이면 미등록으로 변경 후 선수이름, 국가 검색하기
--CASE문으로 사용
--CASE WHEN THEN ELSE END
SELECT PLAYER_NAME, NATION, 
	CASE
		WHEN NATION IS NULL THEN '미등록'
		ELSE '등록'
	END AS "국가 등록 여부"
FROM PLAYER;

--COALESCE(코얼레스) : 합치다, 연동하다
--NULL이 아니면 1차 선택, NULL이면 2차 선택, 둘 다 NULL이면 NULL
SELECT PLAYER_NAME, NICKNAME,
	COALESCE(NICKNAME, PLAYER_NAME) AS KNOWN
FROM PLAYER;

--PLAYER 테이블에서 NICKNAME이 없으면 '별명 없음'으로 변경 후 검색
SELECT PLAYER_NAME, NICKNAME,
	COALESCE(NICKNAME, '별명 없음') AS 별명
FROM PLAYER;

TO_NUMBER()
SELECT TO_NUMBER(SYSDATE-TO_DATE('2000-01-01 00:00:00','YYYY-MM-DD HH24:MI:SS'))/365 FROM dual;
TO_DATE()
TO_CHAR()
SELECT TO_CHAR(TO_NUMBER('30.2')) FROM DUAL;
SELECT TO_CHAR(TO_DATE('2020/01/01','YYYY/MM/DD')) FROM dual;

--집계 함수(NULL은 포함하지 않는다.)
--WHERE절에서는 사용할 수 없다.
--여러개의 값을 하나의 값으로 나타냄
--AVG() 평균
--MAX() 최댓값
--MIN() 최솟값
--SUM()	총합
--COUNT() 갯수
SELECT AVG(HEIGHT), MAX(HEIGHT), MIN(HEIGHT), SUM(HEIGHT) FROM PLAYER;

SELECT * FROM PLAYER;

SELECT COUNT(HEIGHT) FROM PLAYER;

--PLAYER 테이블에서 HEIGTH 개수 검색(NULL포함해서 COUNT하기)
SELECT COUNT(NVL(HEIGHT, 0)) FROM PLAYER;
-----------------------------------------------------------------------------
--ORDER BY : 정렬
--ASC : 오름차순(생략 가능)
--DESC : 내림차순

--PLAYER 테이블에서 키 순으로 검색(오름차순)
SELECT PLAYER_NAME, HEIGHT 
FROM PLAYER
WHERE HEIGHT IS NOT NULL
ORDER BY HEIGHT ASC;

--PLAYER 테이블에서 키 순, 몸무게 순(오름차순)으로 검색
--NULL이 아닌 값만 검색
--첫번째 컬럼값(튜플)이 같으면 두번째 정렬을 한다.
SELECT PLAYER_NAME, HEIGHT, WEIGHT 
FROM PLAYER
WHERE HEIGHT IS NOT NULL AND WEIGHT IS NOT NULL
ORDER BY HEIGHT, WEIGHT;

--GROUP BY : ~별(예 : 포지션 별 평균키)

--SELECT ??? FROM ???
--GROUP BY 컬럼명
--HAVING 조건식

--PLAYER 테이블에서 포지션 검색
SELECT "POSITION"
FROM PLAYER
GROUP BY "POSITION"
HAVING "POSITION" IS NOT NULL;

--WHERE절에서 조건을 처리할 수 있다면 반드시 WHERE절에서 먼저 처리해준다.
--HAVING절보다 더 효율적이다.
SELECT "POSITION"
FROM PLAYER
WHERE "POSITION" IS NOT NULL
GROUP BY "POSITION";

--t_professor 테이블에서 학과별로 교수들 평균 보너스 출력
SELECT deptno, AVG(bonus) FROM t_professor GROUP BY deptno;--NULL 제외
SELECT deptno, ROUND(AVG(nvl(bonus,0)),1) FROM t_professor GROUP BY deptno;

-------------------------실습------------------------------
--EMPLOYEES 테이블에서 JOB_ID별로 평균 SALARY가 10000미만인
--(SALARY 합계, 평균, 최댓값, 최솟값, JOB_ID별 명수) 검색하기
--JOB_ID 알파벳 순으로 정렬(오름차순)
SELECT JOB_ID , SUM(SALARY), AVG(SALARY),
	MAX(SALARY), MIN(SALARY), COUNT(JOB_ID)
FROM EMPLOYEES
GROUP BY JOB_ID
HAVING AVG(SALARY) < 10000
ORDER BY JOB_ID;

--부서별 평균급여를 출력하되, 평균급여가 450 보다 많은 부서만 출력
SELECT deptno, AVG(pay) FROM T_PROFESSOR GROUP BY deptno HAVING AVG(nvl(pay,0))>450;

--PLAYER 테이블에서 몸무게가 80이상인 선수들의 평균키가 180이상인 포지션 검색
SELECT "POSITION", AVG(HEIGHT), MIN(WEIGHT)
FROM PLAYER
WHERE WEIGHT >= 80
GROUP BY "POSITION"
HAVING AVG(HEIGHT) >= 180;

--t_professor 테이블에서 학과별(deptno) 그리고 직급별(position)로 교수들의 평균 급여를 출력
SELECT deptno, position, AVG(pay) FROM t_professor
GROUP BY deptno,position ORDER BY deptno ASC;

--t_emp 테이블에서 매니저별(MGR)로 관리하는 직원들의 ‘매니저’, ‘직원수’와
--‘급여총액’과 ‘급여평균’과 ‘교통비 (COMM) 평균’ 지급액 을 출력
-- 단 사장님은 (job = president)제외 
SELECT MGR,COUNT(*),SUM(nvl(SAL,0)),AVG(nvl(SAL,0)),AVG(nvl(COMM,0))
FROM T_EMP WHERE MGR IS NOT NULL GROUP BY MGR;

--t_professor 테이블에서 직위가 정교수 혹은 조교수 인 사람 중에
--과별(deptno)로 과번호, 소속교수 총수, 근속일 평균, 급여평균, 보너스 평균을 출력
SELECT deptno, count(*), AVG(SYSDATE-hiredate), avg(pay), avg(COALESCE(bonus,0))
FROM T_PROFESSOR WHERE POSITION IN ('정교수','조교수') GROUP BY deptno;

--t_student 테이블에서 학과별(deptno1)로,
--학과번호, 최대몸무게 - 최소몸무게 차이 값을 출력
SELECT deptno1,MAX(weight)-MIN(weight) AS 최대최소몸무게차
FROM T_STUDENT GROUP BY deptno1 HAVING MAX(weight)-MIN(weight)>=30;
















