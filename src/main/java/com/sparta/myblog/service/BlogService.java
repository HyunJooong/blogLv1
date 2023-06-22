package com.sparta.myblog.service;

import com.sparta.myblog.domain.Article;
import com.sparta.myblog.dto.AddArticleRequest;
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

    //blog add write method
    public Article save(AddArticleRequest request){

        return blogRepository.save(request.toEntity());
    }

    //목록조회 메서드
    public List<Article> findAll(){
        return blogRepository.findAll();
    }

    //블로그 글 하나 조회 메서드
    public Article findById(long id){
        return blogRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("not found" + id));

    }

    public void delete(long id){
        blogRepository.deleteById(id);

    }

    // 업데이트 메서드
    @Transactional
    public Article update(Long id , UpdateArticleRequest updateArticleRequest){
        Article article = blogRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("not found" + id));

        article.update(updateArticleRequest.getTitle(),updateArticleRequest.getContent());

        return article;
    }



}
