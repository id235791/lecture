USE sakila;
SELECT * FROM category;
SELECT * FROM film_category;
SELECT * FROM film;
SELECT * FROM language;
SELECT * FROM actor;
SELECT * FROM rental;
SELECT * FROM inventory;
SELECT * FROM payment;
SELECT * FROM customer;
SELECT * FROM address;
SELECT * FROM city;
SELECT * FROM country;

#film, language / MODEL FISH 영화의 언어 검색
SELECT
	f.title,
	l.name
FROM film f
	JOIN language l ON f.language_id = l.language_id
WHERE f.title = 'MODEL FISH';

#customer, address / MELISSA KING 회원의 주소와 핸드폰번호 검색
SELECT
	c.last_name,
	a.address,
    a.phone
FROM customer c
	JOIN address a ON c.address_id = a.address_id
WHERE c.last_name = 'KING';
#city,country / A로 시작하는 도시들이 속한 나라의 이름 검색
SELECT
	c1.city,
    c2.country
FROM city c1
	JOIN country c2 ON c1.country_id = c2.country_id
WHERE c1.city LIKE('A%');
#film_actor, film, actor / MODEL FISH에 출연한 배우들의 이름 검색
SELECT 
	CONCAT(CONCAT(a.first_name,' '),last_name)
FROM film f
	JOIN film_actor fa ON f.film_id = fa.film_id
    JOIN actor a ON fa.actor_id = a.actor_id
WHERE f.title = 'MODEL FISH';

#inventory, customer, film / MODEL FISH 영화를 빌려간 회원들의 이름 검색
SELECT
	c.first_name,
    c.last_name,
    f.title
FROM rental r
	JOIN inventory i ON r.inventory_id = i.inventory_id
    JOIN customer c ON r.customer_id = c.customer_id
    JOIN film f ON i.film_id = f.film_id
WHERE f.title = 'MODEL FISH';

#film_actor, film, actor / JANE JACKMAN이 출연한 영화제목들 검색
SELECT
	f.title
FROM film f
	JOIN film_actor fa ON f.film_id = fa.film_id
    JOIN actor a ON fa.actor_id = a.actor_id
WHERE a.first_name = 'JANE' and a.last_name = 'JACKMAN';

#customer, address, city / JUDITH COX가 사는 도시의 이름 검색
SELECT
	ct.city
FROM customer c
	JOIN address a ON c.address_id = a.address_id
	JOIN city ct ON a.city_id = ct.city_id
WHERE c.last_name = 'COX';