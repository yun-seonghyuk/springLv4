package com.sparta.springlv2.controller;

import com.sparta.springlv2.dto.Message;
import com.sparta.springlv2.dto.PostRequestDto;
import com.sparta.springlv2.dto.PostResponseDto;
import com.sparta.springlv2.entity.User;
import com.sparta.springlv2.jwt.UserDetailsImpl;
import com.sparta.springlv2.service.PostService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.Charset;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @PostMapping("/post")
    public PostResponseDto createPost (@RequestBody PostRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails){
        User user = userDetails.getUser();
        log.info(user.getUsername());
        return postService.createPost(requestDto, user);
    }

    @GetMapping("/posts")
    public List<PostResponseDto> getPosts(){
        return  postService.getPosts();
    }

    @GetMapping("/posts/{id}")
    public PostResponseDto getPost(@PathVariable Long id){
        return postService.getPost(id);
    }

    @PutMapping("/post/update/{id}")
    public PostResponseDto updatePost(@PathVariable Long id, @RequestBody PostRequestDto postRequestDto, @AuthenticationPrincipal UserDetailsImpl userDetails){
        User user = userDetails.getUser();
        return postService.updatePost(id, postRequestDto, user);
    }

    @DeleteMapping("/post/delete/{id}")
    public ResponseEntity<Message> deletePost(@PathVariable Long id,  @AuthenticationPrincipal UserDetailsImpl userDetails) {
        User user = userDetails.getUser();
        log.info(user.getUsername());
        postService.deletePost(id,user);

        Message message = new Message();
        HttpHeaders headers= new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
        message.setMsg("게시글 삭제 성공");
        message.setStatusCode(200);
        return new ResponseEntity<>(message,headers, HttpStatus.OK);
    }
}
