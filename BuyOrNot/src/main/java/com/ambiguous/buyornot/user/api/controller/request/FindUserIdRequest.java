package com.ambiguous.buyornot.user.api.controller.request;

import jakarta.validation.constraints.NotBlank;

public record FindUserIdRequest(
        @NotBlank
        String email,
        @NotBlank
        String userName
) {
}
