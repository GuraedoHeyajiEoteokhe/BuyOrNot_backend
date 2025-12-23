package com.ambiguous.buyornot.mypage.controller.mypageRequest;

public record LikeStockRequest(
        Long userId,
        String likeStock
) {
}
