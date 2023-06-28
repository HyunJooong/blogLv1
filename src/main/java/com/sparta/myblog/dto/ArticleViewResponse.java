package com.sparta.myblog.dto;

import com.sparta.myblog.domain.Article;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Getter
@NoArgsConstructor
public class ArticleViewResponse {

    private Long id;
    private String title;
    private String writer;
    private String content;
    private LocalDateTime createdAt;


    public ArticleViewResponse(Article article) {
        this.id = article.getId();
        this.title = article.getTitle();
        this.title = article.getWriter();
        this.content = article.getContent();
        this.createdAt = article.getCreatedAt();

    }

}
