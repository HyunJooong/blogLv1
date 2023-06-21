package com.sparta.myblog.service;

import com.sparta.myblog.domain.Article;
import com.sparta.myblog.dto.AddArticleRequest;
import com.sparta.myblog.repository.BlogRepository;
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

}
