package com.ambiguous.buyornot.mypage.controller;

import com.ambiguous.buyornot.chatting.api.support.response.ApiResult;
import com.ambiguous.buyornot.mypage.controller.mypageRequest.UpdateRequest;
import com.ambiguous.buyornot.mypage.domain.MypageService;
import com.ambiguous.buyornot.mypage.controller.mypageResponse.UserListResponse;
import com.ambiguous.buyornot.user.entity.User;
import org.springframework.web.bind.annotation.*;

@RestController
public class MyPageController {
    User user;
    MypageService mypageService;


    @GetMapping("/mypage/{id}")
    public ApiResult<UserListResponse> getUser(@PathVariable Long id){
        return ApiResult.success(mypageService.findById(id));
    }

    @PutMapping("/mypage/")
    public ApiResult<?> updateUser(@RequestBody UpdateRequest updateRequest){
        mypageService.updateUser(updateRequest);
        return ApiResult.success();
    }

    @GetMapping("/posting/{id}")
    public ApiResult<?> getPosting(@PathVariable Long id){
        return ApiResult.success(mypageService.getpost(id));
    }

}
