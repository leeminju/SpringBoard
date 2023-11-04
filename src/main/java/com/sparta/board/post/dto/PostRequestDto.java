package com.sparta.board.post.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
public class PostRequestDto {
    private String title;
    private String writer;
    private String contents;
    private String password;
}
