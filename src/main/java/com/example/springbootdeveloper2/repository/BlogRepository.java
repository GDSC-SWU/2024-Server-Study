package com.example.springbootdeveloper2.repository;

import com.example.springbootdeveloper2.domain.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlogRepository extends JpaRepository<Article, Long> {
}
