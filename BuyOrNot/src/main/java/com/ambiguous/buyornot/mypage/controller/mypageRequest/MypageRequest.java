package com.ambiguous.buyornot.mypage.controller.mypageRequest;

public record MypageRequest(
        Long userId,
        String likeStock,
        String OwnStock
) {
}
