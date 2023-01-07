package com.example.mimir.comment.repository;

import static org.assertj.core.api.Assertions.*;

import java.util.List;

import com.example.mimir.article.domain.entity.Article;
import com.example.mimir.article.domain.entity.ArticleFixture;
import com.example.mimir.article.repository.ArticleRepository;
import com.example.mimir.comment.domain.entity.Comment;
import com.example.mimir.comment.domain.entity.CommentFixture;
import com.example.mimir.comment.domain.service.dto.CommentView;
import com.example.mimir.common.util.UuidUtils;
import com.example.mimir.member.domain.entity.Member;
import com.example.mimir.member.domain.entity.MemberFixture;
import com.example.mimir.member.repository.MemberRepository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@SpringBootTest
public class CommentRepositoryTest {
    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private CommentRepository sut;

    @Test
    @DisplayName("댓글 뷰 리스트 조회")
    void commentViews() {
        // given
        Member articleWriter = MemberFixture.noId();
        memberRepository.save(articleWriter);

        Article article = ArticleFixture.noId(articleWriter);
        articleRepository.save(article);

        Member commentWriter = articleWriter;
        Comment comment = CommentFixture.noId(commentWriter, article);
        sut.save(comment);

        // when
        List<CommentView> commentViews = sut.getViewsByArticleId(article.getId());

        // then
        commentViews.forEach(commentView -> {
            assertThat(UuidUtils.bytesToUuid(commentView.writerId())).isNotNull();
            assertThat(commentView.writerNickname()).isNotNull();
            assertThat(commentView.content()).isNotNull();
        });
    }
}
