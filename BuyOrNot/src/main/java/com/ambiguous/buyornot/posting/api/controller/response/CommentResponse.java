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
        boolean pinned,
        boolean deleted,
        LocalDateTime createdAt,
        List<CommentResponse> children
) {
    public static CommentResponse from(Comment comment) {

        String content;
        String userNickname;

        if (!comment.isDeleted()) {
            content = comment.getContent();
            userNickname = comment.getUserNickname();
        } else {
            userNickname = "(알 수 없음)";
            content = switch (comment.getDeleteReason()) {
                case USER -> "작성자에 의해 삭제된 댓글입니다.";
                case REPORT -> "관리자에 의해 재제된 글입니다.";
                default -> "삭제된 댓글입니다.";
            };
        }

        return new CommentResponse(
                comment.getId(),
                comment.getUserId(),
                userNickname,
                content,
                comment.isPinned(),
                comment.isDeleted(),
                comment.getCreatedAt(),
                new ArrayList<>()
        );
    }
}

