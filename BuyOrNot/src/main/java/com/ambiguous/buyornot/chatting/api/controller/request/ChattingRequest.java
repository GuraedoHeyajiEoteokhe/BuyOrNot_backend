package com.ambiguous.buyornot.chatting.api.controller.request;

public record ChattingRequest(
        Long userId,
        Long postId,
        String Message
) {
}
