package me.hakyuwon.springbootdeveloper.repository;

import me.hakyuwon.springbootdeveloper.domain.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlogRepository extends JpaRepository<Article, Long> {
    // JpaRepository 클래스를 상속받을 때 엔티티 article과 엔티티의 PK 타입 Long을 인수로

}
