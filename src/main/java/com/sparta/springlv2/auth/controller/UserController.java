package com.sparta.springlv2.auth.controller;

import com.sparta.springlv2.post.domain.dto.Message;
import com.sparta.springlv2.auth.domain.SignupRequestDto;
import com.sparta.springlv2.auth.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.nio.charset.Charset;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class UserController {

    private final UserService userService;

    @PostMapping("/user/signup")
    public ResponseEntity<Message> signup(@RequestBody SignupRequestDto requestDto){

        userService.signup(requestDto);

        Message message = new Message();
        HttpHeaders headers= new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
        message.setMsg("회원가입 성공");
        message.setStatusCode(200);


        return new ResponseEntity<>(message,headers,HttpStatus.OK);


    }

}
