package com.andersenlab.user_service.service;

import com.andersenlab.user_service.dto.SignupRequest;
import com.andersenlab.user_service.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserFacade {
    final UserRepository userRepository;

    public void signupRequest(SignupRequest signupRequest) {

    }
}
