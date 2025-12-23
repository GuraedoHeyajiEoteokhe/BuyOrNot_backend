package com.ambiguous.buyornot.posting.api.controller.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UpdatePostRequest(

        @NotBlank
        @Size(max = 200)
        String title,

        @NotBlank
        String content
) {
}
