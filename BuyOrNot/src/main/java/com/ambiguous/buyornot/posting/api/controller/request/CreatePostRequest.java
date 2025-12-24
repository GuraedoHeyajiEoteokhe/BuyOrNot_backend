package com.ambiguous.buyornot.posting.api.controller.request;

public record CreatePostRequest(
        Long stockId,
        Long userId,
        PostRequest post
) {
}

