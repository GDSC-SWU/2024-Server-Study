package me.kooyuna.springbootdeveloper.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import me.kooyuna.springbootdeveloper.domain.Article;
import me.kooyuna.springbootdeveloper.dto.AddArticleRequest;
import me.kooyuna.springbootdeveloper.dto.UpdateArticleRequest;
import me.kooyuna.springbootdeveloper.repository.BlogRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor // final이 붙거나 @NotNull이 붙은 필드의 생성자 추가
@Service // 해당 클래스를 빈으로 서블릿 컨테이너에 등록
public class BlogService {
    private final BlogRepository blogRepository;

    //블로그 글 추가 메서드
    public Article save(AddArticleRequest request){
        // JpaRepository에서 지원하는 저장 메서드 save()로
        // AddArticleRequest 클래스에 저장된 값들을 article 데이터베이스에 저장
        return  blogRepository.save(request.toEntity());
    }

    public List<Article> findAll(){
        return blogRepository.findAll(); // JPA 지원 메서드인 findAll 호출해 article 테이블에 저장된 모든 데이터 조회
    }

    public Article findById(long id){
        return blogRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("not found: " + id));
    }

    public void delete(long id){
        blogRepository.deleteById(id);
    }

    // @Transactional 애너테이션: 매칭한 메서드를 트랜잭션으로 묶는 역할
    // -> update() 메서드는 엔티티의 필드 값 바뀌면 중간에 에러 발생해도 제대로 된 값 수정 보장
    @Transactional
    public Article update(long id, UpdateArticleRequest request){
        Article article = blogRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("not found: " + id));

        article.update(request.getTitle(), request.getContent());

        return article;
    }
}
