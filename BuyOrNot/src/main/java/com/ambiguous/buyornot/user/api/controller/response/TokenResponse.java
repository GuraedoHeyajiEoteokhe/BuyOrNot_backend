package com.ambiguous.buyornot.user.api.controller.response;

public record TokenResponse (
        String accessToken,
        String refreshToken
) {
}
