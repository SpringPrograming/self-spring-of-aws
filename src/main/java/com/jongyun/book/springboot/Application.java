package com.jongyun.book.springboot;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/*
* SpringBootApplication: 스프링 부트의 자동설정, 스프링 Bean 읽기와 생성을 모두 자동으로 설정
* * 주의 사항 @SpringBootApplication 이 있는 위치부터 설정을 읽어 가기 때문에
* * 항상 프로젝트의 최상단에 위치해야만 합니다.
 */
@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
