package com.example.mimir.comment.domain.service.dto;

import java.time.LocalDateTime;

public record CommentView(
    /*
     * ID
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
     * 게시글 id
     */
    long articleId,

    /*
     * 내용
     */
    String content,

    /*
     * 작성일시
     */
    LocalDateTime createdAt,

    /*
     * 수정일시
     */
    LocalDateTime updatedAt
) {
}
