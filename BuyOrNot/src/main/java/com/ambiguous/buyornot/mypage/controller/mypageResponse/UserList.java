package com.ambiguous.buyornot.mypage.controller.mypageResponse;

import java.time.LocalDate;

public record UserList(
        String userId,
        String userName,
        String nickName,
        String email,
        LocalDate birth,
        String gender
) {
}
