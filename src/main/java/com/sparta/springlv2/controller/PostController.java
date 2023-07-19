package com.sparta.springlv2.controller;

import com.sparta.springlv2.dto.Message;
import com.sparta.springlv2.dto.PostRequestDto;
import com.sparta.springlv2.dto.PostResponseDto;
import com.sparta.springlv2.entity.User;
import com.sparta.springlv2.service.PostService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.Charset;
import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @PostMapping("/post")
    public PostResponseDto createPost (@RequestBody PostRequestDto requestDto, HttpServletRequest request){
        User user = (User)request.getAttribute("user");
        System.out.println("user.getUsername() = " + user.getUsername());
        return postService.createPost(requestDto, user);
    }

    @GetMapping("/posts")
    public List<PostResponseDto> getPosts(){
        return postService.getPosts();
    }

    @GetMapping("/posts/{id}")
    public PostResponseDto getPost(@PathVariable Long id){
        return postService.getPost(id);
    }

    @PutMapping("/post/update/{id}")
    public PostResponseDto updatePost(@PathVariable Long id, @RequestBody PostRequestDto postRequestDto, HttpServletRequest request){
        User user = (User)request.getAttribute("user");
        System.out.println("user.getUsername() = " + user.getUsername());
        return postService.updatePost(id, postRequestDto, user);
    }

    @DeleteMapping("/post/delete/{id}")
    public ResponseEntity<Message> deletePost(@PathVariable Long id,  HttpServletRequest request) {
        User user = (User)request.getAttribute("user");
        System.out.println("user.getUsername() = " + user.getUsername());
        postService.deletePost(id,user);

        Message message = new Message();
        HttpHeaders headers= new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
        message.setMsg("게시글 삭제 성공");
        message.setStatusCode(200);
        return new ResponseEntity<>(message,headers, HttpStatus.OK);
    }
}
