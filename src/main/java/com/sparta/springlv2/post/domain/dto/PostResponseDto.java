package com.sparta.springlv2.post.domain.dto;

import com.sparta.springlv2.post.domain.entity.Comment;
import com.sparta.springlv2.post.domain.entity.Post;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Data
public class PostResponseDto {

    private Long id;
    private String title;
    private String content;
    private String comment;
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

    public PostResponseDto(Comment comment) {
        this.id = comment.getId();
        this.username = comment.getUser().getUsername();
        this.comment = comment.getComment();
        this.createAt = comment.getCreatedAt();
        this.modifiedAt = comment.getModifiedAt();
    }
}
