package com.ambiguous.buyornot.mypage.controller;

import com.ambiguous.buyornot.chatting.api.support.response.ApiResult;
import com.ambiguous.buyornot.mypage.domain.MypageService;
import com.ambiguous.buyornot.mypage.controller.mypageResponse.UserList;
import com.ambiguous.buyornot.user.entity.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyPageController {
    User user;
    MypageService mypageService;


    @GetMapping("/mypage/{id}")
    public ApiResult<UserList> getUser(@PathVariable Long id){
        return ApiResult.success(mypageService.findById(id));
    }




}
