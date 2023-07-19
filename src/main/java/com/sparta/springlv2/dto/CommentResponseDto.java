package com.sparta.springlv2.dto;

import lombok.Getter;

import java.time.LocalDate;

@Getter
public class CommentResponseDto {

    private Long id;
    private String content;
    private LocalDate createAt;
    private LocalDate modifiedAt;

}
