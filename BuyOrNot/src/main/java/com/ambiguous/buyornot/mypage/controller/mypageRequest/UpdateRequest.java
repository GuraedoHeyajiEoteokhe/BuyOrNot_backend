package com.ambiguous.buyornot.mypage.controller.mypageRequest;

public record UpdateRequest(
        Long id,
        String password,
        String changePassword,
        String userName,
        String nickName,
        String email
) {
}
