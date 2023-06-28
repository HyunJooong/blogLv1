package com.sparta.myblog.dto;

import com.sparta.myblog.domain.Article;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ArticleResponse {

    private String title;
    private String content;
    private String writer;
    private LocalDateTime creatAt;
    private LocalDateTime updateAt;

    private String delMsg;




    public ArticleResponse(Article article) {
        this.title = article.getTitle();
        this.content = article.getContent();
        this.writer = article.getWriter();
        this.creatAt = article.getCreatedAt();
        this.updateAt = article.getUpdatedAt();
    }


    public ArticleResponse(String delMsg){
        this.delMsg = delMsg;
    }

}
