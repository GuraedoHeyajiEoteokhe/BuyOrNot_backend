package com.ambiguous.buyornot.posting.api.controller.response;

import com.ambiguous.buyornot.posting.api.domain.Post;

import java.time.LocalDateTime;

public record PostDetailResponse(
        Long postId,
        Long stockId,
        Long userId,
        String userNickname,
        String title,
        String content,
        long viewCount,
        long likeCount,
        long dislikeCount,
        LocalDateTime createdAt
) {
    public static PostDetailResponse from(Post post) {
        return new PostDetailResponse(
                post.getId(),
                post.getStockId(),
                post.getUserId(),
                post.getUserNickname(),
                post.getTitle(),
                post.getContent(),
                post.getViewCount(),
                post.getLikeCount(),
                post.getDislikeCount(),
                post.getCreatedAt()
        );
    }
}
