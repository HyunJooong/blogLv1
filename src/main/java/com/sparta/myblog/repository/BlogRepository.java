package com.sparta.myblog.repository;

import com.sparta.myblog.domain.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlogRepository extends JpaRepository<Article, Long> {
    
}
