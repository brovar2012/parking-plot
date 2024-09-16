package com.andersenlab.user_service.controller;

import com.andersenlab.user_service.auth.AuthUserService;
import com.andersenlab.user_service.auth.UserService;
import com.andersenlab.user_service.dto.LoginRequest;
import com.andersenlab.user_service.dto.SignupRequest;
import com.andersenlab.user_service.service.UserFacade;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/v1/auth")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserServiceController {

    final AuthenticationManager authenticationManager;
    final UserFacade userService;
    final AuthUserService authUserService;

    @GetMapping("say")
    @PreAuthorize("hasRole('ROLE_SUPER_ADMIN')")
    public String sayHello() {

        return "Hello From Service1";
    }

    @PostMapping("login")
    public ResponseEntity<Object> login(@RequestBody @Valid LoginRequest request) {
//        return authenticationManager.authenticate(request);
        return authUserService.signIn(request);
    }

    @PostMapping("signup")
    public ResponseEntity<Object> signUp(@RequestBody @Valid SignupRequest request) {
//        return authenticationManager.authenticate(request);
        authUserService.signupRequest(request);
        return null;
    }

}
