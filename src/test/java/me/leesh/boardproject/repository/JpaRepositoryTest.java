package me.leesh.boardproject.repository;

import me.leesh.boardproject.config.JpaConfig;
import me.leesh.boardproject.domain.Post;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("JPA 동작 테스트")
@Import(JpaConfig.class)
@DataJpaTest
class JpaRepositoryTest {

    private final PostRepository postRepository;
    private final CommentRepository commentRepository;

    public JpaRepositoryTest(
            @Autowired PostRepository postRepository,
            @Autowired CommentRepository commentRepository) {
        this.postRepository = postRepository;
        this.commentRepository = commentRepository;
    }

    @DisplayName("조회 기본 테스트")
    @Test
    void selectTest() {

        // given

        // when
        List<Post> posts = postRepository.findAll();

        // then
        assertThat(posts)
                .isNotNull()
                .hasSize(2);
    }


    @DisplayName("생성 기본 테스트")
    @Test
    void insertTest() {

        // given
        long previousCount = postRepository.count();

        // when
        postRepository.save(Post.of("new post", "new contents", "#spring"));

        // then
        long postCount = postRepository.count();
        assertThat(postCount).isEqualTo(previousCount + 1);
    }

    @DisplayName("수정 기본 테스트")
    @Test
    void updateTest() {

        // given
        Post post = postRepository.findById(1L).orElseThrow();
        String updatedHashtag = "#springboot";
        post.setHashtag(updatedHashtag);

        // when
        Post savedPost = postRepository.saveAndFlush(post);

        // then
        assertThat(savedPost).hasFieldOrPropertyWithValue("hashtag", updatedHashtag);

    }

    @DisplayName("삭제 기본 테스트")
    @Test
    void delete() {

        // given
        long previousPostCount = postRepository.count();
        long previousCommentCount = commentRepository.count();
        Post post = postRepository.findById(1L).orElseThrow();
        int postCommentSize = post.getComments().size();

        // when
        postRepository.deleteById(1L);

        // then
        assertThat(postRepository.count()).isEqualTo(previousPostCount - 1);
        assertThat(commentRepository.count()).isEqualTo(previousCommentCount - postCommentSize);

    }

}