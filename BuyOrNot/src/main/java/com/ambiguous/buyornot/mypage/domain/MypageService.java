package com.ambiguous.buyornot.mypage.domain;

import com.ambiguous.buyornot.mypage.MypageRepositoty;
import com.ambiguous.buyornot.mypage.controller.mypageRequest.UpdateRequest;
import com.ambiguous.buyornot.mypage.controller.mypageResponse.UserListResponse;
import com.ambiguous.buyornot.user.api.domain.User;

public class MypageService {
    MypageRepositoty mypageRepositoty;


    public UserListResponse findById(Long id){
        User user = mypageRepositoty.findById(id).orElseThrow(() -> new IllegalArgumentException("채팅이 존재하지 않습니다."));
        return new UserListResponse(
                user.getUserId(),
                user.getUserName(),
                user.getNickname(),
                user.getEmail(),
                user.getBirth(),
                user.getGender());
    }

    public void updateUser(UpdateRequest updateRequest) {
        User user = mypageRepositoty.findById(updateRequest.id()).orElseThrow(() -> new IllegalArgumentException("채팅이 존재하지 않습니다."));

        if(updateRequest.email() != null) {
            user.changeEmail(updateRequest.email());
        }
        if(updateRequest.password() != null) {
            if(user.getPassword().equals(updateRequest.password())) {
                user.changePassword(updateRequest.changePassword());
            }
        }
        if(updateRequest.nickName() != null) {
            user.changeNickname(updateRequest.nickName());
        }
        if(updateRequest.userName() != null) {
            user.changeUsername(updateRequest.userName());
        }
    }

    public void getposting(Long id) {

    }
}
