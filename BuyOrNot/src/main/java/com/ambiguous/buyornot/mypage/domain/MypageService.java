package com.ambiguous.buyornot.mypage.domain;

import com.ambiguous.buyornot.mypage.MypageRepositoty;
import com.ambiguous.buyornot.mypage.controller.mypageResponse.UserList;
import com.ambiguous.buyornot.user.entity.User;

public class MypageService {
    MypageRepositoty mypageRepositoty;

    public UserList findById(Long id){
        User user = mypageRepositoty.findById(id);
        return new UserList(
                user.getUserId(),
                user.getUserName(),
                user.getNickname(),
                user.getEmail(),
                user.getBirth(),
                user.getGender());
    }
}
