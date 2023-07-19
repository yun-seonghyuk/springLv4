package com.sparta.springlv2.dto;

import lombok.Getter;

@Getter
public class CommentRequestDto {

    private Long postId;
    private String content;
}
