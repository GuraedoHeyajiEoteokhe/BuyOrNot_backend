package com.ambiguous.buyornot.hotposting.api.controller.response;

import java.time.LocalDateTime;

public record HotPostingSummaryResponse(
        Long postId,
        Long stockId,
        Long userId,
        String userNickname,
        String title,
        long viewCount,
        long likeCount,
        long dislikeCount,
        LocalDateTime createdAt
){}