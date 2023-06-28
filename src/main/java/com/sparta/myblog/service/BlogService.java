package com.sparta.myblog.service;

import com.sparta.myblog.domain.Article;
import com.sparta.myblog.dto.AddArticleRequest;
import com.sparta.myblog.dto.ArticleResponse;
import com.sparta.myblog.dto.UpdateArticleRequest;
import com.sparta.myblog.repository.BlogRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service // 빈으로 생성
@RequiredArgsConstructor //from lombok
public class BlogService {

    private final BlogRepository blogRepository;

    //게시글 작성 및 저장 메서드
    public Article save(AddArticleRequest request) {

        return blogRepository.save(request.toEntity());
    }

    //전체 게시글 목록조회 메서드
    public List<Article> findAll() {

        return blogRepository.findAll();
    }

    //블로그 글 하나 조회 메서드
    public Article findById(long id) {
        return blogRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("not found" + id));

    }

    //삭제 메서드
    public ArticleResponse delete(long id, long password) {

        Article article = blogRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("not found" + id));

        if (article.getPassword().equals(password)) {
            blogRepository.deleteById(id);
            return new ArticleResponse("삭제 되었습니다.");

        } else {
            return new ArticleResponse("다시 입력해주세요.");


        }
    }




    // 업데이트 메서드
    @Transactional
    public Article update(Long id, UpdateArticleRequest updateArticleRequest) {
        Article article = blogRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("not found" + id));

        article.update(updateArticleRequest.getTitle(),
                updateArticleRequest.getContent(),
                updateArticleRequest.getUpdatedAt());

        return article;
    }


}
