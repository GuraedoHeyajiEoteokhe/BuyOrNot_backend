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
        String userNickname = post.getUserId() == null ? "(알 수 없음)" : post.getUserNickname();
        String content = post.isDeleted() ? "관리자에 의해 재제된 글입니다." : post.getContent();

        return new PostDetailResponse(
                post.getId(),
                post.getStockId(),
                post.getUserId(),
                userNickname,
                post.getTitle(),
                content,
                post.getViewCount(),
                post.getLikeCount(),
                post.getDislikeCount(),
                post.getCreatedAt()
        );
    }
}
