package com.sparta.springlv2.dto;

import com.sparta.springlv2.entity.Comment;
import com.sparta.springlv2.entity.Post;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Getter
public class PostResponseDto {

    private Long id;
    private String title;
    private String content;
    private String username;
    private LocalDateTime createAt;
    private LocalDateTime modifiedAt;
    private List<CommentResponseDto> commentList = new ArrayList<>();

    public PostResponseDto(Post post) {
        this.id = post.getId();
        this.title = post.getTitle();
        this.content = post.getContent();
        this.createAt = post.getCreatedAt();
        this.modifiedAt = post.getModifiedAt();
        this.username = post.getUser().getUsername();

        if(post.getComments() != null ){
            for(Comment comment : post.getComments()){
                commentList.add(new CommentResponseDto(comment));
            }
        }
    }
}
