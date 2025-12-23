package com.ambiguous.buyornot.user.api.controller;

import com.ambiguous.buyornot.common.support.response.ApiResult;
import com.ambiguous.buyornot.user.api.controller.request.LoginRequest;
import com.ambiguous.buyornot.user.api.controller.response.TokenResponse;
import com.ambiguous.buyornot.user.api.domain.AuthService;
import com.ambiguous.buyornot.user.api.storage.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final UserRepository userRepository;

    @PostMapping("/login")
    public ApiResult<?> login (@RequestBody LoginRequest request){
        TokenResponse token = authService.login(request);
        return ApiResult.success(token);
    }
}
