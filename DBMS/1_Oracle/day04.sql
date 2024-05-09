SELECT PLAYER_ID AS 선수아이디, PLAYER_NAME AS 선수이름 FROM PLAYER;
--AS 키워드는 생략이 가능하다
SELECT PLAYER_ID 선수아이디, PLAYER_NAME 선수이름 FROM PLAYER;

SELECT PLAYER.PLAYER_NAME, TEAM.ADDRESS FROM PLAYER, TEAM;

SELECT P.PLAYER_NAME, T.ADDRESS FROM PLAYER P, TEAM T;

SELECT * FROM player;
SELECT * FROM TEAM;
SELECT * FROM STADIUM;
--PLAYER 테이블에서 BACK_NO는 등번호, NICKNAME은 선수별명으로 검색
SELECT BACK_NO 등번호, NICKNAME 선수별명 FROM PLAYER;
--STADIUM 테이블에서 ADDRESS, TEAM테이블에서 TEL 검색
SELECT S.ADDRESS,T.TEL FROM STADIUM S,TEAM T;
--PLAYER 테이블에서 TEAM_ID는 소속팀코드, TEAM 테이블에서 TEAM_NAME을 팀이름으로,
--STADIUM 테이블에서 ADDRESS는 주소로 검색
SELECT P.TEAM_ID, T.TEAM_NAME, S.ADDRESS FROM PLAYER P,TEAM T,STADIUM S;
--STADIUM 테이블에서 ADDRESS를 경기장 주소 로 검색
--띄어쓰기가 포함된 별명은 "" 로 묶어준다.
SELECT S.ADDRESS "경기장 주소" FROM STADIUM S;

SELECT PLAYER_NAME|| ' ' || NICKNAME "선수이름 별명" FROM PLAYER;

SELECT * FROM PLAYER
WHERE BIRTH_DATE>=TO_DATE('1987-01-01','YYYY-MM-DD')
AND BIRTH_DATE<=TO_DATE('1989-12-31 23:59:59','YYYY-MM-DD HH24:MI:SS');

SELECT * FROM PLAYER
WHERE BIRTH_DATE BETWEEN TO_DATE('1987-01-01','YYYY-MM-DD')
AND TO_DATE('1989-12-31 23:59:59','YYYY-MM-DD HH24:MI:SS');

SELECT * FROM PLAYER WHERE PLAYER_NAME LIKE('김%');
--SELECT * FROM USER WHERE JUMIN LIKE '______1%'
--위와 같이 사용하면 성별 컬럼 없이 구별이 가능해진다.

--PLAYER 테이블에서 김씨와 이씨인 선수 검색
SELECT * FROM PLAYER WHERE PLAYER_NAME LIKE('김%') OR PLAYER_NAME LIKE('이%');

--STADIUM 중에 경기도에 있는 경기장 검색
SELECT * FROM STADIUM WHERE ADDRESS LIKE ('경기도%');

--PLAYER 테이블에서 이름에 정이 들어가는 선수 검색
SELECT * FROM PLAYER WHERE PLAYER_NAME LIKE('%정%');

SELECT ENAME 사원명, SAL 급여,
	CASE
		WHEN SAL>=3000 THEN 'HIGH'
		WHEN SAL>=1000 THEN 'MID'
		ELSE 'LOW'
	END AS 클래스
FROM EMP;

SELECT ENAME 사원명, SAL 급여,
	CASE
		WHEN SAL>=2000 THEN 1000
		ELSE(
			CASE
				WHEN SAL>=1000 THEN 500
				ELSE 0
			END
		)		
	END 보너스
FROM EMP;

--STADIUM 테이블에서 경기장의 주소와 경기장 이름 좌석 수
--좌석이 0이상 30000 이하면 s 30001 이상 50000 이하면 'M'
--50001이상이면 'L'로 검색

SELECT ADDRESS 주소,STADIUM_NAME 이름, SEAT_COUNT 좌석수,
CASE
	WHEN SEAT_COUNT BETWEEN 0 AND 30000 THEN 'S'
	WHEN SEAT_COUNT BETWEEN 30001 AND 50000 THEN 'M'
	ELSE 'L'
	END "크기"
FROM STADIUM;

--PLAYER 테이블에서 선수 이름, 키, 몸무게
--WEIGHT가 50~70이면 'L' 71~80이면 'M' NULL값이면 '미등록' 다 아니면 'H'
SELECT PLAYER_NAME "선수 이름", HEIGHT 키, WEIGHT 몸무게,
CASE
	WHEN WEIGHT>=50 AND WEIGHT<=70 THEN 'L'
	WHEN WEIGHT>70 AND WEIGHT<=80 THEN 'M'
	WHEN WEIGHT IS NULL THEN '미등록'
	ELSE 'H'
END "옷크기"
FROM PLAYER;

--NULL 처리하기
--NVL(컬럼명,대체할값) : 데이터가 NULL값인 경우 뒤의 대체할 값으로 대신 사용한다.
SELECT PLAYER_NAME, NVL(WEIGHT,0) FROM PLAYER;


--수하물 관리 업체(테이블 개수 : 3 / 컬럼 갯수 3~5)
--목록번호/수량/무게/타입
/*목록번호 	비행기코드(FK)		승객아이디(FK)		수하물 번호(FK)
 * 1		A01				abcd			1
 * 2		A01				sun10			2
 * 3		F03				gil6			3
 * 4		A02				hangle			4
 * 
 * 수하물 테이블
 * 수하물 번호		수량		무게		승객 아이디 	타입
 * 1			2		10		abcd		캐리어
 * 2			12		20		sun10
 * 3			7		20		gil6
 * 4			2		40		hangle
 * 
 * 승객 테이블
 * 아이디/이름/나이/전화번호/
 * abcd		정다솔	20	01011111111
 * sun10	이순신	20	01012341234
 * gil6		홍길동	10	01044556677
 * hangle	세종대왕	30	01018754675
 * 
 * 비행기 테이블
 * 비행기코드/출발지/도착지/항공사
 * K01	서울	뉴욕	아시아나
 * K02	서울	런던	대한항공
 * F03	파리	서울	에어프랑스
 */










