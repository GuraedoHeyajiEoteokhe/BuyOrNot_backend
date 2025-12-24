package com.ambiguous.buyornot.user.api.domain;

import com.ambiguous.buyornot.common.support.error.CoreException;
import com.ambiguous.buyornot.common.support.error.ErrorCode;
import com.ambiguous.buyornot.user.api.controller.request.UserCreateRequest;
import com.ambiguous.buyornot.user.api.storage.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserCommandService {

    private final UserRepository userRepository;
    public final ModelMapper modelMapper;
    public final PasswordEncoder passwordEncoder;

    @Transactional
    public void registerUser(UserCreateRequest request) {

        if (isNullOrEmpty(request.userId()) ||
            isNullOrEmpty(request.password()) ||
            isNullOrEmpty(request.userName()) ||
            isNullOrEmpty(request.email()) ||
            isNullOrEmpty(String.valueOf(request.birth())) ||
            isNullOrEmpty(request.gender()))
        {
            throw new BadCredentialsException("필수 입력 값 누락");
        }

        // 비밀번호 유효성 검사
        if (!isValidPassword(request.password())) {
            throw new BadCredentialsException("비밀번호는 최소 8자리 이상이며, 영문, 숫자, 특수문자(! # $ % & - / _)를 포함해야 합니다.");
        }

        // 성별 검증
        if (!request.gender().equals("F") && !request.gender().equals("M")) {
            throw new BadCredentialsException("성별은 F 또는 M만 가능합니다.");
        }

        if (userRepository.findByUserId(request.userId()).isPresent()) {
            throw new BadCredentialsException("이미 존재하는 아이디 입니다.");
        }

        if (userRepository.findByNickname(request.nickname()).isPresent()) {
            throw new BadCredentialsException("이미 사용중인 닉네임 입니다.");
        }

        if (userRepository.findByEmail(request.email()).isPresent()) {
            throw new BadCredentialsException("이미 사용중인 이메일 입니다.");
        }

        User user = modelMapper.map(request,User.class);
        user.setEncodedPassword(passwordEncoder.encode(request.password()));
        userRepository.save(user);

    }

    // 비밀번호 유효성 검사
    private boolean isValidPassword(String password) {
        if (password.length() < 8) return false;
        return password.matches("^(?=.*[A-Za-z])(?=.*\\d)(?=.*[!#$%&\\-/ _]).+$");
    }


    private boolean isNullOrEmpty(String value) {
        return value == null || value.isBlank();
    }
}
