package com.sparta.springlv2.service;

import com.sparta.springlv2.dto.PostRequestDto;
import com.sparta.springlv2.dto.PostResponseDto;
import com.sparta.springlv2.entity.Post;
import com.sparta.springlv2.entity.User;
import com.sparta.springlv2.repository.CommentRepository;
import com.sparta.springlv2.repository.PostRepository;
import com.sparta.springlv2.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    public PostResponseDto createPost(PostRequestDto requestDto, User user) {
        Post post = new Post(requestDto,user);
        Post savePost = postRepository.save(post);
        return new PostResponseDto(savePost);

    }
    @Transactional(readOnly = true)
    public List<PostResponseDto> getPosts() {
        return postRepository.findAll()
                .stream()
                .map(PostResponseDto::new)
                .toList();
    }

    @Transactional(readOnly = true)
    public PostResponseDto getPost(Long id) {
        Post post = findPost(id);
        return new PostResponseDto(post);
    }


    @Transactional
    public PostResponseDto updatePost(Long id, PostRequestDto postRequestDto, User user) {
        Post post = findPost(id);

        if(!Objects.equals(post.getUser().getId(), user.getId())){
            throw new IllegalArgumentException("회원님이 작성하신 메모가 아닙니다.");
        }
        post.update(postRequestDto);

        return new PostResponseDto(post);
    }


    public void deletePost(Long id, User user) {
        Post post = findPost(id);
        if(!Objects.equals(post.getUser().getId(), user.getId())){
            throw new IllegalArgumentException("회원님이 작성하신 메모가 아닙니다.");
        }

        postRepository.delete(post);
    }

    public Post findPost(Long id) {
        return postRepository.findById(id).orElseThrow(()->
                new IllegalArgumentException("선택한 메모는 존재하지 않습니다."));
    }

}
