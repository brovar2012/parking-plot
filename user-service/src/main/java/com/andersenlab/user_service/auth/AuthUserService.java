package com.andersenlab.user_service.auth;

import com.andersenlab.user_service.dto.JwtTokenResponse;
import com.andersenlab.user_service.dto.LoginRequest;
import com.andersenlab.user_service.dto.SignupRequest;
import com.andersenlab.user_service.entity.User;
import com.andersenlab.user_service.exception.GeneralException;
import com.andersenlab.user_service.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AuthUserService {

    final UserService userService;
    final AuthenticationManager authenticationManager;
    final PasswordEncoder passwordEncoder;
    final UserRepository userRepository;

    public void signupRequest(SignupRequest signupRequest) {
        String email = signupRequest.getEmail();
        Optional<User> userOptional = userRepository.findByEmailContainingIgnoreCase(email);
        if (userOptional.isPresent()) {
            throw new GeneralException("user with email " + email + " already registered");
        }

        String password = passwordEncoder.encode(signupRequest.getPassword());
        User user = User.builder()
                .firstName(signupRequest.getFirstName())
                .lastName(signupRequest.getLastName())
                .email(signupRequest.getEmail())
                .password(password)
                .role(signupRequest.getRole())
                .isActive(true)
                .build();

        userRepository.saveAndFlush(user);
    }

    public ResponseEntity<Object> signIn(LoginRequest request) {
        authenticate(request);
        User user = findByUserEmail(request.getEmail());
        return ResponseEntity.ok(generateTokenForUser(user));
    }

    private void authenticate(LoginRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(),
                request.getPassword()));
    }

    public JwtTokenResponse generateTokenForUser(User user) {
        String token = JwtUtil.generateToken(user);
        return JwtTokenResponse.builder()
                .token(token).build();
    }


    private User findByUserEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new GeneralException("User with email doesn't exist " + email));
    }

}
