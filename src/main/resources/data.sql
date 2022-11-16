insert into post (title, contents, hashtag, created_by, created_at, modified_by, modified_at) values ('안녕하세요. 첫 번째 글 입니다.', '게시판 프로젝트를 시작하고, 첫 번째 글을 작성해보았습니다.', '#Green', 'Wendy', '2022-05-09 20:36:02', 'Arnaldo', '2021-02-14 11:58:16');
insert into comment (contents, created_at, created_by, modified_at, modified_by, post_id) values ('댓글입니다.', '2022-05-09 20:36:02', 'Sunny', '2021-02-14 11:58:16', 'Sunny', 1);

insert into post (title, contents, hashtag, created_by, created_at, modified_by, modified_at) values ('안녕하세요. 두 번째 글 입니다.', '게시판 프로젝트를 시작하고, 두 번째 글을 작성해보았습니다.', '#Red', 'Wendy', '2022-05-09 20:36:02', 'Arnaldo', '2021-02-14 11:58:16');
insert into comment (contents, created_at, created_by, modified_at, modified_by, post_id) values ('두번째 글의 댓글입니다.', '2022-05-09 20:36:02', 'Sunny', '2021-02-14 11:58:16', 'Sunny', 2);
