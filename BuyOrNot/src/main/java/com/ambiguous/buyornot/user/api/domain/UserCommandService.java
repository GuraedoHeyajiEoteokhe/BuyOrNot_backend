package com.ambiguous.buyornot.user.api.domain;

import com.ambiguous.buyornot.common.support.error.CoreException;
import com.ambiguous.buyornot.common.support.error.ErrorCode;
import com.ambiguous.buyornot.user.api.controller.request.UserCreateRequest;
import com.ambiguous.buyornot.user.api.storage.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
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
            isNullOrEmpty(request.gender())) {
        } {
            // 예외처리
        }

        if (userRepository.findByUserId(request.email()).isPresent()) {
            // 예외처리
        }

        if (userRepository.findByNickname(request.nickname()).isPresent()) {
            // 예외처리
        }

        if (userRepository.findByEmail(request.email()).isPresent()) {
            // 예외처리
        }

        User user = modelMapper.map(request,User.class);
        user.setEncodedPassword(passwordEncoder.encode(request.password()));
        userRepository.save(user);

    }

    private boolean isNullOrEmpty(String value) {
        return value == null || value.isBlank();
    }
}
