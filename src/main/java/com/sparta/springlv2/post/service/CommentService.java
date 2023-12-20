package com.sparta.springlv2.post.service;

import com.sparta.springlv2.post.domain.dto.CommentRequestDto;
import com.sparta.springlv2.post.domain.dto.CommentResponseDto;
import com.sparta.springlv2.post.domain.entity.Comment;
import com.sparta.springlv2.post.domain.entity.Post;
import com.sparta.springlv2.auth.domain.entity.User;
import com.sparta.springlv2.post.repository.CommentRepository;
import com.sparta.springlv2.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;

    public CommentResponseDto createComment(CommentRequestDto requestDto, User user, Long id) {
        Post post = findPost(id);
        Comment comment = commentRepository.save(new Comment(user, post,requestDto));
        return new CommentResponseDto(comment);
    }


    @Transactional
    public CommentResponseDto updateComment(Long postId, Long commentId, CommentRequestDto requestDto, User user) {

        Post post = findPost(postId);

        Comment comment = commentRepository.findById(commentId).orElseThrow(
                ()-> new IllegalArgumentException("해당 댓글이 없습니다.")
        );

        if(!comment.getUser().getUsername().equals(user.getUsername())){
            throw new IllegalArgumentException("해당 작성자가 아닙니다.");
        }
        comment.update(requestDto);
        return new CommentResponseDto(comment);
    }

    public void deleteComment(Long postId, Long commentId, User user) {

        findPost(postId);

        Comment comment = commentRepository.findById(commentId).orElseThrow(
                ()-> new IllegalArgumentException("해당 댓글이 없습니다.")
        );

        if(!comment.getUser().getUsername().equals(user.getUsername())){
            throw new IllegalArgumentException("해당 작성자가 아닙니다.");
        }

        commentRepository.delete(comment);
    }


    private Post findPost(Long id) {
        return postRepository.findById(id).orElseThrow(
                ()-> new IllegalArgumentException("해당게시글이 존재하지 않습니다.")
        );
    }
}
