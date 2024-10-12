package me.kooyuna.springbootdeveloper;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing //created_at, updated_at 자동 업데이트
@SpringBootApplication //스프링 부트에 필요한 기본 설정 해주는 애너테이션
public class SpringBootDeveloperApplication { //자바의 메인 메서드와 같은 역할 = 여기서 스프링 부트 시작됨
    public static void main(String[] args){
        SpringApplication.run(SpringBootDeveloperApplication.class,args); //애플리케이션 실행하는 메서드 (메인 클래스로 사용할 클래스, 커맨드 라인의 인수들 전달)
    }
}
