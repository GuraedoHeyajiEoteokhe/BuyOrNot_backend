package com.ambiguous.buyornot.user.api.controller;

import com.ambiguous.buyornot.common.support.response.ApiResult;
import com.ambiguous.buyornot.user.api.controller.request.LoginRequest;
import com.ambiguous.buyornot.user.api.controller.request.LogoutRequest;
import com.ambiguous.buyornot.user.api.controller.response.TokenResponse;
import com.ambiguous.buyornot.user.api.domain.AuthService;
import com.ambiguous.buyornot.user.api.domain.RefreshToken;
import com.ambiguous.buyornot.user.api.domain.User;
import com.ambiguous.buyornot.user.api.storage.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/logout")
    public ApiResult<?> logout(@RequestBody LogoutRequest request){
        authService.logout(request.refreshToken());
        return ApiResult.success();
    }
}
