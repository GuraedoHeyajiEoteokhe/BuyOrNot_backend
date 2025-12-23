package com.ambiguous.buyornot.posting.api.controller.response;

import com.ambiguous.buyornot.posting.api.domain.Post;

import java.time.LocalDateTime;

public record PostResponse(
        Long postId,
        Long stockId,
        Long userId,
        String userNickname,
        String title,
        long viewCount,
        long likeCount,
        long dislikeCount,
        LocalDateTime createdAt
) {
    public static PostResponse from(Post post) {
        return new PostResponse(
                post.getId(),
                post.getStockId(),
                post.getUserId(),
                post.getUserNickname(),
                post.getTitle(),
                post.getViewCount(),
                post.getLikeCount(),
                post.getDislikeCount(),
                post.getCreatedAt()
        );
    }
}

