package me.hakyuwon.springbootdeveloper.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import me.hakyuwon.springbootdeveloper.domain.Article;
import me.hakyuwon.springbootdeveloper.dto.AddArticleRequest;
import me.hakyuwon.springbootdeveloper.dto.UpdateArticleRequest;
import me.hakyuwon.springbootdeveloper.repository.BlogRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class BlogService {

    private final BlogRepository blogRepository;

    public Article save(AddArticleRequest request){
        return blogRepository.save(request.toEntity());
    }

    public List<Article> findAll(){
        return blogRepository.findAll();
    }

    public Article findById(Long id){
        return blogRepository.findById(id).orElseThrow(()->new IllegalArgumentException("not found:" + id));
    }

    public void delete(Long id){
        blogRepository.deleteById(id);
    }

    @Transactional
    public Article update(Long id, UpdateArticleRequest request){
        Article article = blogRepository.findById(id).orElseThrow(()->new IllegalArgumentException("not found:" + id));

        article.update(request.getTitle(), request.getContent());

        return article;
    }
}
