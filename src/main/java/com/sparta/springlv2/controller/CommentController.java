package com.sparta.springlv2.controller;

import com.sparta.springlv2.dto.CommentRequestDto;
import com.sparta.springlv2.dto.CommentResponseDto;
import com.sparta.springlv2.dto.Message;
import com.sparta.springlv2.entity.User;
import com.sparta.springlv2.jwt.UserDetailsImpl;
import com.sparta.springlv2.service.CommentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.Charset;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/post/{postId}/comment")
@Slf4j
public class CommentController {

    private final CommentService commentService;

    @PostMapping
    public CommentResponseDto createComment(@PathVariable Long postId,
                                            @RequestBody CommentRequestDto requestDto,
                                            @AuthenticationPrincipal UserDetailsImpl userDetails){
        User user = userDetails.getUser();
        log.info(user.getUsername());
        return commentService.createComment(requestDto,user,postId);
    }

    @PutMapping("{commentId}")
    public CommentResponseDto updateCommnet(@PathVariable Long postId,
                                            @PathVariable Long commentId,
                                            @RequestBody CommentRequestDto requestDto,
                                            @AuthenticationPrincipal UserDetailsImpl userDetails){
        User user = userDetails.getUser();
        return commentService.updateComment(postId,commentId,requestDto,user);
    }

    @DeleteMapping("{commentId}")
    public ResponseEntity<Message> deleteComment(@PathVariable Long postId,
                                                 @PathVariable Long commentId,
                                                 @AuthenticationPrincipal UserDetailsImpl userDetails){
        User user= userDetails.getUser();
        commentService.deleteComment(postId,commentId, user);

        Message message = new Message();
        HttpHeaders headers= new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
        message.setMsg("삭제성공");
        message.setStatusCode(200);
        return new ResponseEntity<>(message,headers, HttpStatus.OK);
    }
}
