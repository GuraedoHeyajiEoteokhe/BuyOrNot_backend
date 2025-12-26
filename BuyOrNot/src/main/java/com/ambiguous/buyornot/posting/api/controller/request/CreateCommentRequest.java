package com.ambiguous.buyornot.posting.api.controller.request;

import com.ambiguous.buyornot.posting.api.domain.Comment;

public record CreateCommentRequest(
        Long userId,
        String nickName,
        String content,
        Long parentId
) {
    public Comment toEntity(Long postId, Comment parent) {
        return Comment.builder()
                .postId(postId)
                .parent(parent)
                .userId(userId)
                .userNickname(nickName)
                .content(content)
                .build();
    }
}
