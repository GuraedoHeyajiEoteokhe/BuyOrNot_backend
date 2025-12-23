package com.ambiguous.buyornot.posting.api.controller.request;

import jakarta.validation.constraints.NotBlank;

public record UpdateCommentRequest(
        @NotBlank String content
) {
}
