package com.ambiguous.buyornot.posting.api.controller.request;

import com.ambiguous.buyornot.posting.api.domain.Post;
import jakarta.validation.constraints.NotBlank;

public record PostRequest(
        @NotBlank String title,
        @NotBlank String content
) {
    public Post toEntity(Long stockId, Long userId, String userNickname) {
        return Post.builder()
                .stockId(stockId)
                .userId(userId)
                .userNickname(userNickname)
                .title(title)
                .content(content)
                .build();
    }
}
