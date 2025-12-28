package com.ambiguous.buyornot.posting.api.controller.request;

import jakarta.validation.constraints.NotBlank;

public record UpdateCommentRequest(
        Long userId,
        @NotBlank String content
) {
}
