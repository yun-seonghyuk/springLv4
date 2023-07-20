package com.sparta.springlv2.service;

import com.sparta.springlv2.dto.LoginRequestDto;
import com.sparta.springlv2.dto.SignupRequestDto;
import com.sparta.springlv2.entity.User;
import com.sparta.springlv2.jwt.JwtUtil;
import com.sparta.springlv2.repository.UserRepository;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public void signup(SignupRequestDto requestDto) {
        String username = requestDto.getUsername();
        String password = passwordEncoder.encode(requestDto.getPassword());

        Optional<User> checkUsername = userRepository.findByUsername(username);
        if(checkUsername.isPresent()){
            throw new IllegalArgumentException("중복된 사용자가 존재합니다.");
        }
        String email = requestDto.getEmail();

        Optional<User> checkEmail = userRepository.findByEmail(email);
        if(checkEmail.isPresent()){
            throw new IllegalArgumentException("중복된 Email 입니다.");

        }
        User user = new User(username, password,email);
        userRepository.save(user);

    }

}
