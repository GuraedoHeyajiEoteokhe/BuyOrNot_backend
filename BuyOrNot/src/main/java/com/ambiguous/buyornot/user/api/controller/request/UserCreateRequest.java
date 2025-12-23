package com.ambiguous.buyornot.user.api.controller.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;
public record UserCreateRequest(
        @JsonProperty("user_id")
        String userId,
        String password,
        @JsonProperty("user_name") String userName,
        String nickname,
        String email,
        LocalDate birth,
        String gender
) {

}
