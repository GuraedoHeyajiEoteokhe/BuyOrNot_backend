package com.ambiguous.buyornot.user.api.controller.request;

public record LoginRequest(
        String userId,
        String password
) {
}
