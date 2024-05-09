USE sakila;
#SUB_QUERY : SQL문 내부에 SQL문 선언
#FROM		: IN LINE VIEW
#SELECT		: SCALAR
#WHERE		: SUB_QUERY

SELECT * FROM city;

SELECT
	(SELECT country FROM country WHERE country_id = (SELECT country_id FROM city WHERE city = 'Aden')) country,
	city 
FROM city WHERE country_id = (SELECT country_id FROM city WHERE city = 'Aden');
SELECT country_id FROM city WHERE city = 'Aden';

#ROWNUM 이용법
SELECT * FROM rental order by customer_id;
SELECT customer_id, COUNT(*) cnt FROM rental GROUP BY customer_id ORDER BY 2 DESC;
SELECT
	@ROWNUM := @ROWNUM+1 rownum,
    t.customer_id,
    t.cnt
FROM 
	(SELECT customer_id, COUNT(*) cnt FROM rental GROUP BY customer_id ORDER BY 2 DESC) t,
    (SELECT @ROWNUM:=0) R
;
