package com.sparta.springlv2.dto;

import com.sparta.springlv2.entity.Post;
import com.sparta.springlv2.entity.User;
import lombok.Getter;

@Getter
public class PostRequestDto {

    private String title;
    private String content;
    private User user;
    private Post post;
}
