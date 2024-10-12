package me.kooyuna.springbootdeveloper.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import me.kooyuna.springbootdeveloper.domain.Article;
import me.kooyuna.springbootdeveloper.dto.AddArticleRequest;
import me.kooyuna.springbootdeveloper.dto.UpdateArticleRequest;
import me.kooyuna.springbootdeveloper.repository.BlogRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest // 테스트용 애플리케이션 컨텍스트
@AutoConfigureMockMvc // MockMvc 생성 및 자동 구성
class BlogServiceTest {

    @Autowired
    protected MockMvc mockMvc;

    @Autowired
    private WebApplicationContext context;

    @Autowired
    BlogRepository blogRepository;

    @BeforeEach
    public void mockMvcSetUp() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(context)
                .build();
        blogRepository.deleteAll();
    }

    @DisplayName("save: 블로그 글 저장 성공")
    @Test
    public void save() throws Exception {
        //given
        final String title = "title";
        final String content = "content";
        final AddArticleRequest request = new AddArticleRequest(title, content);

        //when
        blogRepository.save(request.toEntity());

        //then
        List<Article> articles = blogRepository.findAll();

        assertThat(articles.size()).isEqualTo(1);
        assertThat(articles.get(0).getTitle()).isEqualTo(title);
        assertThat(articles.get(0).getContent()).isEqualTo(content);
    }

    @DisplayName("findAll: article 테이블의 모든 내용 조회 성공")
    @Test
    public void findAll() throws Exception {
        //given
        final String title1 = "title1";
        final String content1 = "content1";
        final String title2 = "title2";
        final String content2 = "content2";

        blogRepository.save(Article.builder()
                .title(title1)
                .content(content1)
                .build());
        blogRepository.save(Article.builder()
                .title(title2)
                .content(content2)
                .build());

        //when
        List<Article> articles = blogRepository.findAll();

        //then
        assertThat(articles.size()).isEqualTo(2);
        assertThat(articles.get(0).getTitle()).isEqualTo(title1);
        assertThat(articles.get(0).getContent()).isEqualTo(content1);
        assertThat(articles.get(1).getTitle()).isEqualTo(title2);
        assertThat(articles.get(1).getContent()).isEqualTo(content2);
    }

    @DisplayName("findById: id로 article 테이블 데이터 조회 성공")
    @Test
    public void findById() throws Exception {
        //given
        final Article article = Article.builder()
                .title("title")
                .content("content")
                .build();
        blogRepository.save(article);

        //when
        Article foundArticle = blogRepository.findById(article.getId()).get();

        //then
        assertThat(foundArticle.getId()).isEqualTo(article.getId());
        assertThat(foundArticle.getTitle()).isEqualTo(article.getTitle());
        assertThat(foundArticle.getContent()).isEqualTo(article.getContent());

    }

    @DisplayName("findById: id로 article 테이블 조회 실패 예외처리 성공")
    @Test
    public void findByIdException() throws Exception {
        //given
        BlogService blogService = new BlogService(blogRepository);
        final Article article = Article.builder()
                .title("title")
                .content("content")
                .build();
        // 이 전까지는 article id는 null, save하는 순간 자동으로 정해짐
        blogRepository.save(article);
        final long wrongId = article.getId() - 1;

        //when
        //assertThrows(에러 class, 에러가 발생해야 하는 로직)
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class,
                () -> blogService.findById(wrongId)); //저장된 article의 id와 다른 id로 조회

        //then
        assertThat(e.getMessage())
                .isEqualTo("not found: " + wrongId);
    }

    @DisplayName("delete: id로 article 테이블 데이터 삭제 성공")
    @Test
    public void delete() throws Exception {
        //given
        final Article article = Article.builder()
                .title("title")
                .content("content")
                .build();
        blogRepository.save(article);

        //when
        blogRepository.deleteById(article.getId());

        //then
        List<Article> articles = blogRepository.findAll();
        assertThat(articles).isEmpty();
    }

    @DisplayName("update: article 테이블의 데이터 수정 성공")
    @Test
    @Transactional
    public void update() throws Exception {
        //given
        final Article article = blogRepository.save(Article.builder()
                .title("title")
                .content("content")
                .build());

        final String newTitle = "newTitle";
        final String newContent = "newContent";
        UpdateArticleRequest request = new UpdateArticleRequest(newTitle, newContent);

        Article savedArticle = blogRepository.findById(article.getId())
                .orElseThrow(() -> new IllegalArgumentException("not found: " + article.getId()));

        //when
        savedArticle.update(request.getTitle(), request.getContent());

        //then
        savedArticle = blogRepository.findById(article.getId()).get();
        assertThat(savedArticle.getTitle()).isEqualTo(newTitle);
        assertThat(savedArticle.getContent()).isEqualTo(newContent);
    }
}