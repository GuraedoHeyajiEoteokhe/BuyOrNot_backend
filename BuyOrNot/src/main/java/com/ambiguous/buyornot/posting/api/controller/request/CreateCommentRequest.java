package com.ambiguous.buyornot.posting.api.controller.request;

import com.ambiguous.buyornot.posting.api.domain.Comment;

public record CreateCommentRequest(
        String content,
        Long parentId
) {
    public Comment toEntity(Long postId, Long userId, String userNickname, Comment parent
    ) {
        return Comment.builder()
                .postId(postId)
                .parent(parent)
                .userId(userId)
                .userNickname(userNickname)
                .content(content)
                .build();
    }
}
