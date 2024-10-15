package com.example.springbootdeveloper2.dto;

import com.example.springbootdeveloper2.domain.Article;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class AddArticleRequest {
    private String title;
    private String content;

    public Article toEntity(){ //생성자를 사용해 개체 생성
        return Article.builder()
                .title(title)
                .content(content)
                .build();
    }
}
