package com.ambiguous.buyornot.posting.api.controller.response;

import com.ambiguous.buyornot.posting.api.domain.Post;

import java.time.LocalDateTime;

public record PostListResponse(
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
    public static PostListResponse from(Post post) {
        return new PostListResponse(
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

