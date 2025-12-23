package com.ambiguous.buyornot.posting.api.controller.response;

import com.ambiguous.buyornot.posting.api.domain.Comment;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public record CommentResponse(
        Long commentId,
        Long userId,
        String userNickname,
        String content,
        boolean deleted,
        LocalDateTime createdAt,
        List<CommentResponse> children
) {
    public static CommentResponse from(Comment comment) {
        return new CommentResponse(
                comment.getId(),
                comment.getUserId(),
                comment.getUserNickname(),
                comment.isDeleted() ? "삭제된 댓글입니다." : comment.getContent(),
                comment.isDeleted(),
                comment.getCreatedAt(),
                new ArrayList<>()
        );
    }
}

