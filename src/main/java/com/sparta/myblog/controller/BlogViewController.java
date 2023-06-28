package com.sparta.myblog.controller;

import com.sparta.myblog.domain.Article;
import com.sparta.myblog.dto.ArticleListViewResponse;
import com.sparta.myblog.dto.ArticleViewResponse;
import com.sparta.myblog.service.BlogService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class BlogViewController {

    private final BlogService blogService;

    //전체 게시글 조회
    @GetMapping("/articles")
    public String getArticles(Model model){
        List<ArticleListViewResponse> articleListViewResponses = blogService.findAll().stream()
                .map(ArticleListViewResponse::new)
                .toList();
        model.addAttribute("articles", articleListViewResponses);

        return "articleList";
    }

    //조회
    @GetMapping("/articles/{id}")
    public String getArticle(@PathVariable Long id, Model model){
        Article article = blogService.findById(id);
        model.addAttribute("article", new ArticleViewResponse(article));

        return "article";
    }

    // 생성
    @GetMapping("/new-article")
    public String newArticle(@RequestParam(required = false) Long id, Model model)
    {
        if(id == null ) {
            model.addAttribute("article", new ArticleViewResponse());
        }

        return "newArticle";
    }

}
