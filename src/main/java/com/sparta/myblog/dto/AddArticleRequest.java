package com.sparta.myblog.dto;

import com.sparta.myblog.domain.Article;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.security.Timestamp;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class AddArticleRequest {

    private String title;
    private String content;
    private String writer;
    private Long password;


    public Article toEntity(){
        return Article.builder()
                .title(title)
                .content(content)
                .writer(writer)
                .password(password)
                .build();
    }

}
