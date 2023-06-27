package com.sparta.myblog.dto;

import com.sparta.myblog.domain.Article;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ArticleResponse {

    private final String title;
    private final String writer;
    private final String content;
    private final LocalDateTime creatAt;


    public ArticleResponse(Article article) {
        this.title = article.getTitle();
        this.content = article.getContent();
        this.writer = article.getWriter();
        this.creatAt = article.getCreatedAt();
    }


}
