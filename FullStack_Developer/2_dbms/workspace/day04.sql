use gb;
#database : gb
select * from `order`;
select * from flower;
insert into `order` values('반하나','01012341234','서울시 강남구 대치동',null,'p002');

#inner join
select o.name, o.phone, f.name, f.color, o.pot_id
from `order` o
	inner join flower f on o.flower_id = f.flower_id;
    
#outer join
select
	o.name,
	o.phone,
    ifnull(f.name,'미정') "name",
    ifnull(f.color,'미정') "color",
    o.pot_id
from `order` o left outer join flower f on o.flower_id = f.flower_id;

use world;
#database : world

#limit
select * from country;
select * from country where continent = 'Asia' order by name desc limit 10;
select * from country where continent = 'Asia' order by name desc limit 10,10;
select * from country where continent = 'Asia' order by name desc limit 11,1;

#Sub Query
select name, population, avg(population) 평균인구수 from country;
select 100 from country;
select avg(population) from country;
select name, population, 100 평균인구수 from country;
select name, population, (select avg(population) from country) 평균인구수 from country;
select name, population from country where population>
	(select avg(population) from country);
select name, population, gnp
from
	(select * from country where continent='Asia') t;
    
select * from country where continent='Asia';
    
use sakila;
#database : sakila

select * from category;
select * from film;
select * from film_category;

select
	f.title 영화제목,
    c.name 카테고리
from film_category fc
	join category c on fc.category_id = c.category_id
    join film f on f.film_id = fc.film_id;

#model fish 영화의 언어 검색
select * from film;
select * from language;
select
	f.title,
    l.name
from film f
	join language l on f.language_id = l.language_id
where f.title = 'MODEL FISH';
#melissa king 회원의 주소와 핸드폰번호 검색
select * from customer;
select * from address;
select 
	concat(c.first_name,' ',c.last_name) "name",
    a.address,
    a.phone
from customer c
	join address a on c.address_id = a.address_id
where c.first_name = 'melissa';
#A로 시작하는 도시들이 속한 나라의 이름과 그 도시명 검색
select * from city;
select * from country;
select
	c1.city,
    c2.country
from city c1
	join country c2 on c1.country_id = c2.country_id
where c1.city like('A%');
#model fish에 출연한 배우들의 이름 검색
select * from film;
select * from actor;
select * from film_actor;
select
	f.title,
    a.first_name,
    a.last_name
from film_actor fa
	join actor a on a.actor_id = fa.actor_id
    join film f on fa.film_id = f.film_id
where f.title = 'MODEL FISH';
#model fish 영화를 빌려간 회원들의 이름 검색
select * from rental;
select * from inventory;
select
	c.first_name,
    c.last_name,
    f.title
from rental r
	join inventory i on r.inventory_id = i.inventory_id
    join customer c on r.customer_id = c.customer_id
    join film f on i.film_id = f.film_id
where f.title = 'MODEL FISH';
#jane jackman이 출연한 영화제목들 검색
select
	f.title
from film_actor fa
	join film f on fa.film_id = f.film_id
    join actor a on fa.actor_id = a.actor_id
where a.first_name = 'JANE' and a.last_name = 'JACKMAN';
#judith cox가 사는 도시의 이름 검색
select
	ct.city
from customer c
	join address a on c.address_id = a.address_id
    join city ct on a.city_id = ct.city_id
where c.last_name = 'COX';

insert into test (strdata) values('JDBC data1');

