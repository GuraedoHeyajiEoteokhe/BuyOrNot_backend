package com.ambiguous.buyornot.user.api.controller;

import com.ambiguous.buyornot.common.support.response.ApiResult;
import com.ambiguous.buyornot.user.api.controller.request.UserCreateRequest;
import com.ambiguous.buyornot.user.api.domain.UserCommandService;
import io.swagger.v3.oas.models.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
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

}
