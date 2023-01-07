package com.example.mimir.article.domain.service.dto.view;

import java.time.LocalDateTime;

public record ArticleDetailView (
    /*
     * 게시글 ID
     */
    long id,

    /*
     * 작성자 ID
     */
    byte[] writerId,

    /*
     * 작성자 닉네임
     */
    String writerNickname,

        /*
     * 제목
     */
    String title,

    /*
     * 내용
     */
    String content,

    /*
     * 조회수
     */
    long viewCount,

    /*
     * 생성 년월일시
     */
    LocalDateTime createdAt,

    /*
     * 수정 년월일시
     */
    LocalDateTime updatedAt
) {
}
