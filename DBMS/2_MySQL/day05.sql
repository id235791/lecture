USE testworld;
#문자열 연결, 문자열 길이
SELECT CONCAT('He','llo'), LENGTH('FLOWER') FROM DUAL;
#올림, 버림, 반올림
SELECT CEIL(3.14), FLOOR(3.14), ROUND(3.14,2) FROM dual;
#널처리
USE world;
SELECT * FROM country;
SELECT name, gnp, IFNULL(gnpold,'기록없음') from country;
#그룹함수
SELECT COUNT(population), SUM(population), AVG(population), MAX(population), MIN(population) FROM country;
SELECT COUNT(gnpold), COUNT(*) FROM country;

#ORDER BY
#gnp 내림차순
SELECT name, population, gnp FROM country ORDER BY gnp DESC;
#첫번째 컬럼값이 같으면 두번째 정렬 기준을 따른다.
SELECT name, population, gnp FROM country ORDER BY gnp DESC, population ASC;

USE sakila;
#film / 등급(rating)별로 영화 개수 검색하기
SELECT rating, COUNT(*) FROM film GROUP BY rating;

#대륙별로 평균 gnp 검색
SELECT Continent, AVG(gnp) FROM country GROUP BY continent;
#gnp 평균이 100000 이상인 지역들의 지역명, gnp 최대값, gnp 최소값, gnp 평균 검색
SELECT region,max(gnp), min(gnp), avg(gnp) from country group by region HAVING avg(gnp)>=100000;
#대륙별 평균 인구를 출력하되 15000000명을 넘는 대륙만 검색
SELECT AVG(population) FROM country GROUP BY continent HAVING AVG(population)>15000000;
#인구수가 2000만명을 넘는 나라들의 평균 넓이가 2000000 제곱km를 넘는 대륙들만 검색 
SELECT AVG(surfacearea) FROM country WHERE Population>20000000 GROUP BY continent;
#대륙별, 그리고 지역별로 나라들의 평균 수명 검색
SELECT Continent,Region,avg(LifeExpectancy) FROM country GROUP BY Continent,Region;
#아시아, 유럽에 속한 나라들 중에 정치제도별로 정치제도, 나라수, 평균 수명, 평균 gnp 검색
SELECT GovernmentForm, count(*), avg(LifeExpectancy), avg(gnp)
FROM country WHERE continent IN ('Asia','Europe') GROUP BY GovernmentForm
ORDER BY avg(gnp) DESC;

