package me.kooyuna.springbootdeveloper.repository;

import me.kooyuna.springbootdeveloper.domain.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlogRepository extends JpaRepository<Article, Long> {
}
