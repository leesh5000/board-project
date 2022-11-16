package me.leesh.boardproject.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Getter
@ToString
@Table(indexes = {
        @Index(columnList = "contents"),
        @Index(columnList = "createdAt"),
        @Index(columnList = "createdBy")
})
@EntityListeners(AuditingEntityListener.class)
@Entity
public class Comment {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // 댓글 번호

    @Setter @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Post post; // 게시글

    @Setter @Column(nullable = false, length = 500)
    private String contents; // 본문

    @CreatedDate @Column(nullable = false)
    private LocalDateTime createdAt; // 생성일

    @CreatedBy @Column(nullable = false, length = 100)
    private String createdBy; // 생성자

    @LastModifiedDate @Column(nullable = false)
    private LocalDateTime modifiedAt; // 수정일

    @LastModifiedBy @Column(nullable = false, length = 100)
    private String modifiedBy; // 수정자

    protected Comment() {}

    private Comment(Post post, String contents) {
        this.post = post;
        this.contents = contents;
    }

    public static Comment of(Post post, String contents) {
        return new Comment(post, contents);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Comment comment)) return false;
        return id != null && id.equals(comment.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}
