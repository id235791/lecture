-- php 회원가입
INSERT INTO tb_member (mem_userid, mem_userpw, mem_name, mem_hp, mem_email, mem_hobby, mem_ssn1, mem_ssn2, mem_zipcode, mem_address1, mem_address2, mem_address3) VALUES ('apple', '1111', '김사과', '010-1111-1111', 'apple@apple.com', '영화감상', '001011', '4068518', '12345', '서울 서초구 양재동', '111-11', '101동');

-- 테스트
INSERT INTO tb_member (mem_userid, mem_userpw, mem_name, mem_hp, mem_email, mem_hobby, mem_ssn1, mem_ssn2, mem_zipcode, mem_address1, mem_address2, mem_address3) VALUES ('avocado', '1111', '안카도', '010-1111-1111', 'avocado@avocado.com', '쇼핑 게임 ', '001011', '3068518', '13480', '경기 성남시 분당구 대왕판교로 477', '11111', ' (판교동)');

select * from tb_member;

-- php 로그인
SELECT mem_userid FROM tb_member WHERE mem_userid='banana' and mem_userpw='2221';

-- 비밀번호 암호화
select md5('1111') as password; -- b59c67bf196a4758191e42f76670ceba
select md5('1234') as password; -- 81dc9bdb52d04dc20036dbd8313ed055



