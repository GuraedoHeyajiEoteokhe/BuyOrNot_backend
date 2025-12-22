package com.ambiguous.buyornot.mypage.controller.mypageResponse;

import java.time.LocalDate;

public record UserListResponse(
        String userId,
        String userName,
        String nickName,
        String email,
        LocalDate birth,
        String gender
) {
}
