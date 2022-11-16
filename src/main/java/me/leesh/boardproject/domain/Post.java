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
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

@Getter
@ToString
@Table(indexes = {
        @Index(columnList = "title"),
        @Index(columnList = "hashtag"),
        @Index(columnList = "createdAt"),
        @Index(columnList = "createdBy")
})
@EntityListeners(AuditingEntityListener.class)
@Entity
public class Post {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // 게시글 번호

    @Setter @Column(nullable = false, length = 255)
    private String title; // 제목

    @Setter @Column(nullable = false, length = 10000)
    private String contents; // 본문

    @Setter @Column(nullable = true, length = 255)
    private String hashtag; // 해시태그

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, fetch = FetchType.LAZY) @OrderBy("id") @ToString.Exclude
    private final Set<Comment> comments = new LinkedHashSet<>();

    @CreatedDate @Column(nullable = false)
    private LocalDateTime createdAt; // 생성일

    @CreatedBy @Column(nullable = false, length = 100)
    private String createdBy; // 생성자

    @LastModifiedDate @Column(nullable = false)
    private LocalDateTime modifiedAt; // 수정일

    @LastModifiedBy @Column(nullable = false, length = 100)
    private String modifiedBy; // 수정자

    protected Post() {}

    private Post(String title, String contents, String hashtag) {
        this.title = title;
        this.contents = contents;
        this.hashtag = hashtag;
    }

    public static Post of(String title, String contents, String hashtag) {
        return new Post(title, contents, hashtag);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Post post)) return false;
        return id != null && id.equals(post.id); // 아직 영속화가 되지 않아서 id=null 이면, 모두 동등성 검사 탈락
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
