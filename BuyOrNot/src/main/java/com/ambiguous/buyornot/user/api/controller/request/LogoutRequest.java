package com.ambiguous.buyornot.user.api.controller.request;

import jakarta.validation.constraints.NotBlank;

public record LogoutRequest(
        @NotBlank(message = "refreshToken은 필수입니다.")
        String refreshToken
) {
}
