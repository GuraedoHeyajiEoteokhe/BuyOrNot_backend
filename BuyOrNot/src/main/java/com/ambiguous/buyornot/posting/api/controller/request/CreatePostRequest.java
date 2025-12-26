package com.ambiguous.buyornot.posting.api.controller.request;

public record CreatePostRequest(
        Long userId,
        String nickname,
        PostRequest post
) {
}
