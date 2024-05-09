use frontend;

select * from tb_member;

-- 아이디 중복체크
select mem_idx from tb_member where mem_userid='ryu';

-- 게시판 테이블 만들기
create table tb_board(
	b_idx bigint auto_increment primary key,
    b_title varchar(500) not null,
    b_content text,
    b_userid varchar(20) not null,
    b_hit int default 0,
    b_regdate datetime default now(),
    b_like int default 0
);

desc tb_board;

insert into tb_board (b_userid, b_title, b_content) values ('apple', '안녕', '반가워~!');

select b_idx, b_title, b_userid, b_hit, b_regdate, b_like from tb_board order by b_idx desc;
select * from tb_board;

-- 글보기
select b_idx, b_title, b_content, b_userid, b_hit, b_regdate, b_like from tb_board where b_idx=2;

-- 조회수 1 올리기
update tb_board set b_hit = b_hit + 1 where b_idx=2; 

-- 좋아요  1 올리기
update tb_board set b_like = b_like + 1 where b_idx=2;

-- 좋아요 현재 값을 리턴
select b_like from tb_board where b_idx=2;


-- 글 수정
update tb_board set b_title='수정1', b_content='수정2' where b_idx=2;

-- 글 삭제
delete from tb_board where b_idx=3;

-- 댓글 테이블
create table tb_reply(
	re_idx bigint auto_increment primary key,
    re_userid varchar(20) not null,
    re_content varchar(1000) not null,
    re_regdate datetime default now()
);









