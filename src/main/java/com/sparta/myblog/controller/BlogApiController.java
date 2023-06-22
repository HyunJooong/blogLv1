package com.sparta.myblog.controller;

import com.sparta.myblog.domain.Article;
import com.sparta.myblog.dto.AddArticleRequest;
import com.sparta.myblog.dto.ArticleResponse;
import com.sparta.myblog.dto.UpdateArticleRequest;
import com.sparta.myblog.service.BlogService;
import jakarta.websocket.server.PathParam;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController // HTTP Response BOdy 에 객체 데이터를 JSON 형식으로 반환하는 컨트롤러
public class BlogApiController {

    private final BlogService blogService;

    @PostMapping("/api/articles")

    public ResponseEntity<Article> addArticle(@RequestBody AddArticleRequest request){

        Article savedArticle = blogService.save(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(savedArticle);
    }

    @GetMapping("/api/articles")
    public ResponseEntity<List<ArticleResponse>> findAllArticles(){
        List<ArticleResponse> articleResponses = blogService.findAll()
                .stream()
                .map(ArticleResponse::new)
                .toList();

        return ResponseEntity.ok().body(articleResponses);
    }

    @GetMapping("/api/articles/{id}")
    //url 경로에서 값 추출
    public ResponseEntity<ArticleResponse> findArticle(@PathVariable long id){
        Article article = blogService.findById(id);

        return ResponseEntity.ok().body(new ArticleResponse(article));
    }

    @DeleteMapping("/api/articles/{id}")
    public ResponseEntity<Void> deleteArticle(@PathVariable long id){
        blogService.delete(id);

        return ResponseEntity.ok().build();
    }

    @PutMapping("/api/articles/{id}")
    public ResponseEntity<Article> updateArticle(@PathVariable Long id,
                                                 @RequestBody UpdateArticleRequest updateArticleRequest) {
        Article updateArticle = blogService.update(id, updateArticleRequest);

        return ResponseEntity.ok().body(updateArticle);
    }
}
