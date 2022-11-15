package me.leesh.boardproject.domain;

import java.time.LocalDateTime;

public class Post {

    private Long id; // 게시글 번호
    private String title; // 제목
    private String contents; // 본문
    private String hashtag; // 해시태그

    private LocalDateTime createdAt; // 생성일
    private String createdBy; // 생성자
    private LocalDateTime modifiedAt; // 수정일
    private String modifiedBy; // 수정자

}
