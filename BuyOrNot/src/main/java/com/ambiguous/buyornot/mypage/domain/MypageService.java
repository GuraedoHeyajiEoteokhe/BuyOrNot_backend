package com.ambiguous.buyornot.mypage.domain;

import com.ambiguous.buyornot.mypage.MypageRepositoty;
import com.ambiguous.buyornot.mypage.controller.mypageRequest.UpdateRequest;
import com.ambiguous.buyornot.mypage.controller.mypageResponse.UserListResponse;
import com.ambiguous.buyornot.user.entity.User;

public class MypageService {
    MypageRepositoty mypageRepositoty;

    public UserListResponse findById(Long id){
        User user = mypageRepositoty.findById(id);
        return new UserListResponse(
                user.getUserId(),
                user.getUserName(),
                user.getNickname(),
                user.getEmail(),
                user.getBirth(),
                user.getGender());
    }

    public void updateUser(UpdateRequest updateRequest) {
        User user = mypageRepositoty.findById(updateRequest.id());

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

    public Object getpost(Long id) {
    }
}
