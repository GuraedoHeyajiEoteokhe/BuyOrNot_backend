package com.ambiguous.buyornot.posting.api.controller.request;

import jakarta.validation.constraints.Size;

public record UpdatePostRequest(
        Long userId,
        @Size(max = 200) String title,
        String content
) {
}
