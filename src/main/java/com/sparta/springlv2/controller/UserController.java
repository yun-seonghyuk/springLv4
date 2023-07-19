package com.sparta.springlv2.controller;

import com.sparta.springlv2.dto.LoginRequestDto;
import com.sparta.springlv2.dto.Message;
import com.sparta.springlv2.dto.SignupRequestDto;
import com.sparta.springlv2.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
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

//    @PostMapping("/user/login")
//    public ResponseEntity<Message> login(@RequestBody LoginRequestDto requestDto , HttpServletResponse res){
//
//        userService.login(requestDto,res);
//        Message message = new Message();
//        HttpHeaders headers= new HttpHeaders();
//        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
//        message.setMsg("로그인 성공");
//        message.setStatusCode(200);
//        return new ResponseEntity<>(message,headers,HttpStatus.OK);
//    }

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
