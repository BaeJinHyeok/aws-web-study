package com.study.awswebstudy.web.dto;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;
@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = RANDOM_PORT)
class IndexControllerTest {

    //Execution failed for task ':test'. Error 발생 > 해결방법 > File -> Setting -> Bulid ... -> Gradel -> Run tests using 을 기존 Gradle 에서 Intellij IDEA 로 바꿔줌
    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void 메인페이지_로딩() {
        //when
        String body = this.restTemplate.getForObject("/", String.class);

        //then
        assertThat(body).contains("웹 서비스");

//        index.mustache UTF-8 설정에도 불구하고 HTML 한글 깨짐 현상으로 테스트 실패 -> application.properties 에 밑 코드를 추가 후 정상 실행
//        server.servlet.encoding.charset=UTF-8
//        server.servlet.encoding.enabled=true
//        server.servlet.encoding.force=true
    }
}