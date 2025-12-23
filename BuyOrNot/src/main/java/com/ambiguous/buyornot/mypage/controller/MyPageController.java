package com.ambiguous.buyornot.mypage.controller;

import com.ambiguous.buyornot.common.support.response.ApiResult;
import com.ambiguous.buyornot.mypage.controller.mypageRequest.LikeStockRequest;
import com.ambiguous.buyornot.mypage.controller.mypageRequest.MypageRequest;
import com.ambiguous.buyornot.mypage.controller.mypageRequest.UpdateRequest;
import com.ambiguous.buyornot.mypage.controller.mypageResponse.PostResponse;
import com.ambiguous.buyornot.mypage.domain.MypageService;
import com.ambiguous.buyornot.mypage.controller.mypageResponse.UserListResponse;
import com.ambiguous.buyornot.user.entity.User;
import org.springframework.web.bind.annotation.*;

@RestController
public class MyPageController {
    MypageService mypageService;


    @GetMapping("/mypage/{id}")
    public ApiResult<UserListResponse> getUser(@PathVariable Long id){
        return ApiResult.success(mypageService.findById(id));
    }

    @PutMapping("/mypage/user")
    public ApiResult<?> updateUser(@RequestBody UpdateRequest updateRequest){
        mypageService.updateUser(updateRequest);
        return ApiResult.success();
    }

    @GetMapping("/mypage/posting/{userId}")
    public ApiResult<PostResponse> getPosting(@PathVariable Long userid){
        return ApiResult.success(mypageService.getposting(userid));
    }

    @PostMapping("/mypage/")
    public ApiResult<?> createMypage(@RequestBody MypageRequest mypageRequest){
        mypageService.createMypage(mypageRequest);
        return ApiResult.success();
    }

    @PutMapping("/mypage/stock")
    public ApiResult<?> updateStock(@RequestBody MypageRequest mypageRequest){
        mypageService.changeStock(mypageRequest);
        return ApiResult.success();
    }

}
