package com.study.awswebstudy;

import com.study.awswebstudy.config.auth.dto.SessionUser;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing // JPA Auditing 활성화 -> BaseTimeEntity
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
