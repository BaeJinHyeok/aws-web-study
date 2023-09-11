package com.study.awswebstudy;

import com.study.awswebstudy.config.auth.dto.SessionUser;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

//@EnableJpaAuditing // JPA Auditing 활성화 -> BaseTimeEntity ..... -> EnableJpaAuditing을 사용하기 위해선 최소 하나의 @Entity가 필요함 -> 하지만 @WebMvcTest이다 보니 당연히 없음
                                                                        //EnableJpaAuditing가 @SpringBootApplication 과 함께있으니 @WebMvcTest에서도 스캔하게 되어있음
                                                                        // 그래서 EnableJpAuditing과 @SpringBootApplication 을 분리함 -> 제거 후 config 패키지에 JpaConfig를 생성하며 추가
@SpringBootApplication
public class AwsWebStudyApplication {

    public static void main(String[] args) {
        SpringApplication.run(AwsWebStudyApplication.class, args);

//        SessionUser user = new SessionUser();
        String test = org.springframework.core.SpringVersion.getVersion();
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>Spring version :"+ test);
//        System.out.println(user.getName() + user.getEmail() + user.getPicture());
    }

}
