package com.ambiguous.buyornot.posting.api.controller.response;

public record BestPostSummaryResponse(
        Long postId,
        Long stockId,
        Long userId,
        String userNickname,
        String title,
        long viewCount,
        long likeCount,
        long dislikeCount
) {

}
