package com.sparta.myblog.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "post")
public class Article {

    @Id //primaryKey setting
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 기본키 자동으로 1씩 증가
    @Column(name = "id", updatable = false)
    private Long id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "writer", nullable = false)
    private String writer;

    @Column(name = "password", nullable = false)
    private Long password;

    @Column(name = "content", nullable = false)
    private String content;

    @CreatedDate //엔티티 생성 시간 저장
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @LastModifiedDate // 엔티티 수정 시간 저장
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;


    @Builder //builder annotation from lombok
    public Article(String title, String writer,
                   String content, Long password,
                   LocalDateTime createdAt) {
        this.title = title;
        this.content = content;
        this.writer = writer;
        this.password = password;
        this.createdAt = createdAt;

    }

    public void update(String title, String content, LocalDateTime updatedAt) {
        this.title = title;
        this.content = content;
        this.updatedAt = updatedAt;
    }


}
