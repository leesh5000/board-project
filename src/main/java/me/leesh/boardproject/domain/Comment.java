package me.leesh.boardproject.domain;

import java.time.LocalDateTime;

public class Comment {

    private Long id; // 댓글 번호
    private Post post; // 게시글
    private String contents; // 본문

    private LocalDateTime createdAt; // 생성일
    private String createdBy; // 생성자
    private LocalDateTime modifiedAt; // 수정일
    private String modifiedBy; // 수정자

}
