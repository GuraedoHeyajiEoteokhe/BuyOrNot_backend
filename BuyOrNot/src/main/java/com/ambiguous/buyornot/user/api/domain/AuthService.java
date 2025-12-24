package com.ambiguous.buyornot.user.api.domain;

import com.ambiguous.buyornot.common.jwt.JwtTokenProvider;
import com.ambiguous.buyornot.user.api.controller.request.LoginRequest;
import com.ambiguous.buyornot.user.api.controller.response.TokenResponse;
import com.ambiguous.buyornot.user.api.storage.RefreshTokenRepository;
import com.ambiguous.buyornot.user.api.storage.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    private final RefreshTokenRepository refreshTokenRepository;

    public TokenResponse login(LoginRequest request) {

        if (request.userId() == null || request.userId().isEmpty() ||
                request.password() == null || request.password().isEmpty()) {
            throw new BadCredentialsException("아이디 또는 비밀번호가 null값입니다.");
        }

        User user = userRepository.findByUserId(request.userId())
                .orElseThrow(() -> new BadCredentialsException("아이디 또는 비밀번호가 잘못 되었습니다. 아이디와 비밀번호를 정확히 입력해 주세요."));

        if(!passwordEncoder.matches(request.password(), user.getPassword())) {
            throw new BadCredentialsException("아이디 또는 비밀번호가 잘못 되었습니다. 아이디와 비밀번호를 정확히 입력해 주세요.");
        }

        String accessToken = jwtTokenProvider.createAccessToken(user.getUserId(), user.getRole().name(), user.getId());
        System.out.println("accessToken: " + accessToken);
        String refreshToken = jwtTokenProvider.createRefreshToken(user.getUserId());
        System.out.println("refreshToken: " + refreshToken);

        RefreshToken refreshTokenEntity = RefreshToken.builder()
                .userId(user.getUserId())
                .refreshToken(refreshToken)
                .expires(jwtTokenProvider.getRefreshTokenExpiryDate(refreshToken))
                .build();

        refreshTokenRepository.save(refreshTokenEntity);

        return new TokenResponse(accessToken, refreshToken);

    }
}