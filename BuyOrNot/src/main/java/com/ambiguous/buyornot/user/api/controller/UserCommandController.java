package com.ambiguous.buyornot.user.api.controller;

import com.ambiguous.buyornot.common.support.response.ApiResult;
import com.ambiguous.buyornot.user.api.controller.request.UserCreateRequest;
import com.ambiguous.buyornot.user.api.controller.request.UserWithdrawRequest;
import com.ambiguous.buyornot.user.api.domain.UserCommandService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserCommandController {

    private final UserCommandService userCommandService;

    @PostMapping("/regist")
    public ApiResult<?> registerUser (@RequestBody UserCreateRequest request){
        userCommandService.registerUser(request);
        return ApiResult.success();
    }

    @DeleteMapping("/users/me")
    public ApiResult<?> withdrawUser (@RequestBody UserWithdrawRequest request){
        userCommandService.withdrawUser(request);
        return ApiResult.success();
    }

}
